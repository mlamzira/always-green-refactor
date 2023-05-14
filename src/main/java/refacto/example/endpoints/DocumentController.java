package refacto.example.endpoints;

import refacto.example.CreateDocumentCommand;
import refacto.example.DocumentService;

import java.io.ByteArrayInputStream;

public class DocumentController {
    public final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void postDocument(DocumentDto documentDto) {
        CreateDocumentCommand command = new CreateDocumentCommand();
        command.setRelativePath(documentDto.relativePath);
        command.setName(documentDto.name);
        command.setShouldNotify(true);
        command.setSinisterCode(documentDto.sinisterCode);
        command.setDocumentAsInputStream(new ByteArrayInputStream(documentDto.content));
        command.setUploaderDomain(documentDto.source);
        command.setUploaderDomainMetadata(documentDto.meta);
        documentService.create(command);
    }

    public static class DocumentDto {
        public String relativePath;
        public String name;
        public String sinisterCode;

        public byte[] content;
        public String source;
        public String meta;
    }
}
