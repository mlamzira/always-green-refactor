package refacto.example;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class _JurassicDiskDocument extends _JurassicDiskArtifact{
    private boolean isSecure;
    private boolean isDisposable;
    private String scheme;
    private String folderId;
    private InputStream content;

    public _JurassicDiskDocument(boolean isSecure, boolean isDisposable, String scheme) {
        super();
        this.isSecure = isSecure;
        this.isDisposable = isDisposable;
        this.scheme = scheme;
    }

}
