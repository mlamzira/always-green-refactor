package refacto.example;

import java.io.InputStream;

public class CreateDocumentCommand {
    private InputStream documentAsInputStream;
    private String uploaderDomain;
    private String uploaderDomainMetadata;
    private String sinisterCode;
    private String name;
    private Boolean shouldNotify;
    private String relativePath;

    public String getRelativePath() {
        return relativePath;
    }

    public String getDocumentName() {
        return name;
    }

    public String getSinisterCode() {
        return sinisterCode;
    }

    public InputStream getDocumentAsInputStream() {
        return documentAsInputStream;
    }

    public void setDocumentAsInputStream(InputStream documentAsInputStream) {
        this.documentAsInputStream = documentAsInputStream;
    }

    public Boolean shouldNotify() {
        return shouldNotify;
    }

    public String getUploaderDomain() {
        return uploaderDomain;
    }

    public void setUploaderDomain(String uploaderDomain) {
        this.uploaderDomain = uploaderDomain;
    }

    public String getUploaderDomainMetadata() {
        return uploaderDomainMetadata;
    }

    public void setUploaderDomainMetadata(String uploaderDomainMetadata) {
        this.uploaderDomainMetadata = uploaderDomainMetadata;
    }

    public void setSinisterCode(String sinisterCode) {
        this.sinisterCode = sinisterCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShouldNotify(Boolean shouldNotify) {
        this.shouldNotify = shouldNotify;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
