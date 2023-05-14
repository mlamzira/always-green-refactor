package refacto.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JurassicDiskDocumentServiceTest {

    private DocumentService documentService;
    private JDFolderService JDFolderService;
    private ArtifactService artifactService;
    private JDDocumentRepository JDDocumentRepository;
    private SinisterService sinisterService;
    private AuthenticationService authenticationService;
    private DomainEventPublisher domainEventPublisher;

    @BeforeEach
    public void setUp() {
        JDFolderService = Mockito.mock(JDFolderService.class);
        artifactService = Mockito.mock(ArtifactService.class);
        JDDocumentRepository = Mockito.mock(JDDocumentRepository.class);
        sinisterService = Mockito.mock(SinisterService.class);
        authenticationService = Mockito.mock(AuthenticationService.class);
        domainEventPublisher = Mockito.mock(DomainEventPublisher.class);

        documentService = new DocumentService(JDFolderService,
                JDDocumentRepository,
                authenticationService,
                domainEventPublisher);
    }

    @Test
    public void testCreateDocument() {
        // Prepare test data
        String documentName = "test.pdf";
        String documentRootId = UUID.randomUUID().toString();
        String sinisterCode = "SC123";
        InputStream documentContent = new ByteArrayInputStream("Test content".getBytes());
        CreateDocumentCommand command = new CreateDocumentCommand();

        // Prepare mock behavior
        when(JDFolderService.createRelativePathFolders(any(), any(), any())).thenReturn("parentFolderId");
        when(JDDocumentRepository.upload(any())).thenReturn(new DocumentId("documentId"));
        when(authenticationService.isClient()).thenReturn(true);

        // Call the method to test
        DocumentId result = documentService.create(command);

        // Check the result
        assertEquals("documentId", result.get());

        // Verify interactions with mocks
        verify(JDFolderService).createRelativePathFolders(any(), any(), any());
        verify(artifactService).updateAuthors(any());
        verify(JDDocumentRepository).upload(any());
        verify(sinisterService).updateActor(any());
    }
}
