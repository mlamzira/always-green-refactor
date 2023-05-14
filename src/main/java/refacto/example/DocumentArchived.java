package refacto.example;

import lombok.Builder;

import java.util.Date;

@Builder
public class DocumentArchived {
    String documentId;
    String documentName;
    String sinisterCode;
    String uploaderDomain;
    String uploadUser;
    Date uploadDate;
    String uploaderDomainMetadata;
}
