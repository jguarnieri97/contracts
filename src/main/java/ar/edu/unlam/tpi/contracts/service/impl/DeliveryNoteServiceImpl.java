package ar.edu.unlam.tpi.contracts.service.impl;

import ar.edu.unlam.tpi.contracts.client.BlockchainServiceClient;
import ar.edu.unlam.tpi.contracts.dto.DeliveryNoteRequest;
import ar.edu.unlam.tpi.contracts.exception.DeliveryNoteServiceInternalException;
import ar.edu.unlam.tpi.contracts.model.DeliveryNote;
import ar.edu.unlam.tpi.contracts.persistence.WorkContractDAO;
import ar.edu.unlam.tpi.contracts.service.DeliveryNoteService;
import ar.edu.unlam.tpi.contracts.service.task.DeliveryNoteExecutorTask;
import ar.edu.unlam.tpi.contracts.util.Converter;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final WorkContractDAO repository;
    private final BlockchainServiceClient blockchainClient;
    private final ExecutorService executorService;

    @Override
    public void createDeliveryNote(DeliveryNoteRequest request) {
        log.info("Creando delivery-note, request recibido: {}", Converter.convertToString(request));

        var contract = repository.findWorkContractById(request.getContractId());

        var file = createFile(request);
        var deliveryNote = new DeliveryNote(contract, file);

        contract.setDeliveryNote(deliveryNote);

        repository.saveWorkContract(contract);

        executorService.execute(new DeliveryNoteExecutorTask(repository, blockchainClient, contract));
    }

    private byte[] createFile(DeliveryNoteRequest request) {
        try {
            log.info("::: Comienza el proceso de creación de archivo :::");
            var document = new Document();
            var baos = new ByteArrayOutputStream();

            log.info("::: Abriendo archivo :::");
            PdfWriter.getInstance(document, baos);
            document.open();

            log.info("::: Creando - Sección encabezado :::");
            this.buildDocumentHeaderPart(document);

            log.info("::: Creando - Sección Prooveedor :::");
            this.buildDocumentSupplierPart(request, document);

            document.add(new LineSeparator());

            log.info("::: Creando - Sección Cliente :::");
            this.buildDocumentClientPart(request, document);

            document.add(new LineSeparator());

            log.info("::: Creando - Sección Cuerpo :::");
            this.buildDocumentTablePart(request, document);

            log.info("::: Cerrando el archivo :::");
            document.close();

            log.info("::: FIN del proceso de creación de archivo :::");

            return baos.toByteArray();
        } catch (DocumentException e) {
            log.error("Error al crear el archivo: {}", e.getMessage());
            throw new DeliveryNoteServiceInternalException(e.getMessage());
        }
    }

    private void buildDocumentHeaderPart(Document document) throws DocumentException {
        var titleFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
        document.add(new Paragraph("Remito", titleFont));
    }

    private void buildDocumentSupplierPart(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph("N° " + request.getBodyData().getNoteNumber()));
        document.add(new Paragraph(request.getSupplierData().getCompanyName()));
        document.add(new Paragraph("CUIT: " + request.getSupplierData().getCuit()));
    }

    private void buildDocumentClientPart(DeliveryNoteRequest request, Document document) throws DocumentException {
        document.add(new Paragraph("Cliente: " + request.getApplicantData().getCompanyName()));
        document.add(new Paragraph("CUIT: " + request.getApplicantData().getCuit()));
    }

    private void buildDocumentTablePart(DeliveryNoteRequest request, Document document) throws DocumentException {
        var table = new PdfPTable(2);
        table.addCell("Descripción");
        table.addCell("Precio");

        var data = request.getBodyData().getDescriptionData();

        data.forEach(row -> {
            table.addCell(row.getDetail());
            table.addCell(row.getPrice().toString());
        });
        document.add(table);
    }
}
