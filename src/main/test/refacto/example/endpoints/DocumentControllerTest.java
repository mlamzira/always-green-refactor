package refacto.example.endpoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import refacto.example.*;

class DocumentControllerTest {

    private DocumentController documentController;

    @BeforeEach
    void setUp() {
        documentController = new DocumentController(new DocumentService(
                new JDFolderService(),
                new JDDocumentRepository(),
                new AuthenticationService(),
                new DomainEventPublisher()
        ));
    }

    @Test
    void name() {
        DocumentController.DocumentDto documentDto = new DocumentController.DocumentDto();
        documentDto.meta = "sinistre-accident-123"
        documentDto.source = "some-other-service";
        documentDto.name = "constat.pdf";
        documentDto.content = "hello toto".getBytes();
        documentDto.relativePath = "/constat";
        documentDto.sinisterCode = "sinstre-123";

        documentController.postDocument(documentDto);
    }
}