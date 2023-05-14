package refacto.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class DocumentService {
    public static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private final JDFolderService jDFolderService;
    private final JDDocumentRepository jDDocumentRepository; // TODO DIP
    private final AuthenticationService authenticationService;
    private final DomainEventPublisher domainEventPublisher;

    public DocumentService(JDFolderService jDFolderService,
                           JDDocumentRepository jDDocumentRepository,
                           AuthenticationService authenticationService,
                           DomainEventPublisher domainEventPublisher) {
        this.jDFolderService = jDFolderService;
        this.jDDocumentRepository = jDDocumentRepository;
        this.authenticationService = authenticationService;
        this.domainEventPublisher = domainEventPublisher;
    }

    //T0DO: This method has too many responsibilities
    public DocumentId create(final CreateDocumentCommand createDocumentCommand) {
        String relativePath = createDocumentCommand.getRelativePath();
        String fileName = createDocumentCommand.getDocumentName();

        String documentParentId = jDFolderService.createRelativePathFolders(relativePath, createDocumentCommand.getSinisterCode(), createDocumentCommand.getSinisterCode());

        _JurassicDiskDocument _jdd = new _JurassicDiskDocument(false, false, "X://*.pdf");
        _jdd.setName(fileName);
        _jdd.setFolderId(documentParentId);
        _jdd.setContent(createDocumentCommand.getDocumentAsInputStream());

        DocumentId documentId = jDDocumentRepository.upload(_jdd);

        _jdd.setId(documentId.get());

        if (Boolean.TRUE.equals(createDocumentCommand.shouldNotify())) {

            User user = authenticationService.getConnectedUser();
            log.info("Document {} added successfully by user {}",_jdd.getId(), user.getId());

            domainEventPublisher.publishEvent(DocumentArchived.builder()
                    .documentId(_jdd.getId())
                    .documentName(_jdd.getName())
                    .sinisterCode(createDocumentCommand.getSinisterCode())
                    .uploaderDomain(createDocumentCommand.getUploaderDomain())
                    .uploadUser(user.getId())
                    .uploadDate(new Date())
                    .uploaderDomainMetadata(createDocumentCommand.getUploaderDomainMetadata())
                    .build());
        }
        return new DocumentId(_jdd.getId());
    }
}
