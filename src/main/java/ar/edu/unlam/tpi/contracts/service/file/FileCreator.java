package ar.edu.unlam.tpi.contracts.service.file;

import ar.edu.unlam.tpi.contracts.dto.request.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteServiceInternalException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Slf4j
@Component
public class FileCreator {

    public byte[] createFile(DeliveryNoteRequest request) {
        try {
            log.info("::: Comienza el proceso de creación de archivo :::");
            var document = new Document();
            var baos = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, baos);
            document.open();

            buildHeader(document);
            buildSupplierSection(request, document);
            buildClientSection(request, document);
            buildTableSection(request, document);

            document.close();
            log.info("::: FIN del proceso de creación de archivo :::");

            return baos.toByteArray();
        } catch (DocumentException e) {
            log.error("Error al crear el archivo: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException(e.getMessage());
        }
    }

    private void buildHeader(Document document) throws DocumentException {
        var titleFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
        document.add(new Paragraph("Remito", titleFont));
    }

    private void buildSupplierSection(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph("N° " + request.getBodyData().getNoteNumber()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(request.getSupplierData().getCompanyName()));
        document.add(new Paragraph("Email: " + request.getSupplierData().getEmail()));
        document.add(new Paragraph("Teléfono: " + request.getSupplierData().getPhone()));
        document.add(new Paragraph("Dirección: " + request.getSupplierData().getAddress()));
        document.add(new Paragraph("CUIT: " + request.getSupplierData().getCuit()));
        document.add(new Paragraph(" "));
    }

    private void buildClientSection(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Cliente: " + request.getApplicantData().getCompanyName()));
        document.add(new Paragraph("Email: " + request.getApplicantData().getEmail()));
        document.add(new Paragraph("Teléfono: " + request.getApplicantData().getPhone()));
        document.add(new Paragraph("Dirección: " + request.getApplicantData().getAddress()));
        document.add(new Paragraph("CUIT: " + request.getApplicantData().getCuit()));
        document.add(new Paragraph(" "));
    }

    private void buildTableSection(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph(" "));
        var table = new PdfPTable(2);
        table.addCell("Descripción");
        table.addCell("Precio");

        double total = request.getBodyData().getDescriptionData().stream()
                .mapToDouble(row -> {
                    table.addCell(row.getDetail());
                    table.addCell(row.getPrice().toString());
                    return row.getPrice();
                }).sum();

        var totalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        table.addCell(new Paragraph("Total:", totalFont));
        table.addCell(String.valueOf(total));

        document.add(table);
    }

}
