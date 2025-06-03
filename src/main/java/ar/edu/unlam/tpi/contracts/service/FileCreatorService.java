package ar.edu.unlam.tpi.contracts.service;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteServiceInternalException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.model.WorkContractEntity;
import ar.edu.unlam.tpi.contracts.util.FileImageUtil;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.Image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
public class FileCreatorService {

    public byte[] createFile(DeliveryNoteRequest request, WorkContractEntity contract) {
        try {
            log.info("::: Comienza el proceso de creación de archivo :::");
            var document = new Document();
            var baos = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, baos);
            document.open();

            buildHeader(document);
            buildSupplierSection(request,contract, document);
            buildClientSection(request, document);
            buildTableSection(request,contract, document);

            document.close();
            log.info("::: FIN del proceso de creación de archivo :::");

            return baos.toByteArray();
        } catch (DocumentException e) {
            log.error("Error al crear el archivo: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException(e.getMessage());
        }
    }

    public byte[] signFile(DeliveryNote deliveryNote, String signatureBase64) {
        try {
            PdfReader reader = retrievePdfReader(deliveryNote.getData());
            Map<String,Object> stamperData = retrievePdfStamper(deliveryNote.getData(), reader);
            PdfStamper stamper = (PdfStamper) stamperData.get("stamper");
            ByteArrayOutputStream outputStream = (ByteArrayOutputStream) stamperData.get("outputStream");
            buildImageSignatureSection(signatureBase64, reader, stamper);
            stamper.close();
            reader.close();
            return outputStream.toByteArray();            
        } catch (Exception e) {
            log.error("Error al firmar el archivo: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException("Error al firmar el archivo: " + e.getMessage());
        }
    }

    private void buildImageSignatureSection(String signatureBase64, PdfReader reader, PdfStamper stamper) throws Exception {
        Image signatureImage = FileImageUtil.buildImage(signatureBase64,100,100);
        Map<String, Float> coords = buildCoordsToBuildSignature(reader, signatureImage);
        
        PdfContentByte content = stamper.getOverContent(reader.getNumberOfPages());
        content.addImage(signatureImage, signatureImage.getScaledWidth(), 0, 0, signatureImage.getScaledHeight(), 
            coords.get("x"), coords.get("y"));
    }

    private Map<String, Float> buildCoordsToBuildSignature(PdfReader reader, Image signatureImage) throws Exception {
        Rectangle pageSize = reader.getPageSize(reader.getNumberOfPages());
        Float pageWidth = pageSize.getWidth();
        Float x = pageWidth - signatureImage.getScaledWidth() - 50;
        Float y = 50f;
        
        return Map.of("x", x, "y", y);
    }


    private PdfReader retrievePdfReader(byte[] data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            PdfReader reader = new PdfReader(bais);
            log.info("Devolviendo el PDF a partir de un array de bytes");
            return reader;
        } catch (Exception e) {
            log.error("Error al recuperar el PDF desde los bytes: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException("Error al recuperar el PDF desde los bytes: " + e.getMessage());
        }
    }

    private Map<String, Object> retrievePdfStamper(byte[] data, PdfReader reader) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, baos);
            log.info("Devolviendo el stamper para el PDF");
            return Map.of("stamper", stamper, "outputStream", baos);            
        } catch (Exception e) {
            log.error("Error al crear el stamper del PDF: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException("Error al crear el stamper del PDF: " + e.getMessage());
        }
    }

    private void buildHeader(Document document) throws DocumentException {
        var titleFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
        document.add(new Paragraph("Remito", titleFont));
    }

    private void buildSupplierSection(DeliveryNoteRequest request,WorkContractEntity contract, Document document) throws DocumentException {
        document.add(new Paragraph("N° " + contract.getCodeNumber()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(request.getSuppliersData().get(0).getCompanyName()));
        document.add(new Paragraph("Email: " + request.getSuppliersData().get(0).getEmail()));
        document.add(new Paragraph("Teléfono: " + request.getSuppliersData().get(0).getPhone()));
        document.add(new Paragraph("Dirección: " + request.getSuppliersData().get(0).getAddress()));
        document.add(new Paragraph("CUIT: " + request.getSuppliersData().get(0).getCuit()));
        document.add(new Paragraph(" "));
    }

    private void buildClientSection(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Cliente: " + request.getApplicantsData().get(0).getCompanyName()));
        document.add(new Paragraph("Email: " + request.getApplicantsData().get(0).getEmail()));
        document.add(new Paragraph("Teléfono: " + request.getApplicantsData().get(0).getPhone()));
        document.add(new Paragraph("Dirección: " + request.getApplicantsData().get(0).getAddress()));
        document.add(new Paragraph("CUIT: " + request.getApplicantsData().get(0).getCuit()));
        document.add(new Paragraph(" "));
    }

    private void buildTableSection(DeliveryNoteRequest request,WorkContractEntity contract, Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        var table = new PdfPTable(2);
        table.addCell("Descripción");
        table.addCell("Precio");
        table.addCell(contract.getDetail());
        table.addCell(contract.getPrice().toString());

        double total = contract.getPrice();
        
        var totalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        table.addCell(new Paragraph("Total:", totalFont));
        table.addCell(String.valueOf(total));

        document.add(table);
    }

}
