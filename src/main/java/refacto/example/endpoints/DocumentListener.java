package refacto.example.endpoints;

import refacto.example.CreateDocumentCommand;
import refacto.example.DocumentService;

import java.io.ByteArrayInputStream;

public class DocumentListener {
    private final DocumentService documentService;

    public DocumentListener(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void onDocumentGeneratedEvent(DocumentGeneratedEvent event) {
        CreateDocumentCommand command = new CreateDocumentCommand();
        command.setRelativePath(event.relativePath);
        command.setName(event.documentName);
        command.setShouldNotify(false);
        command.setSinisterCode(event.sinsterCode);
        command.setDocumentAsInputStream(new ByteArrayInputStream(event.content));
        command.setUploaderDomain(null);
        command.setUploaderDomainMetadata(null);
        documentService.create(command);
    }

    public static class DocumentGeneratedEvent {
        public String relativePath;
        public String documentName;
        public String sinsterCode;
        public byte[] content;
    }
}
