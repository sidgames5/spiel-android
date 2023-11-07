package social.spielapp.android.util.types;

import java.net.URI;

public class Author {
    public final String username;
    public final String displayName;
    public final URI picture;

    public Author(String username, String displayName, URI picture) {
        this.username = username;
        this.displayName = displayName;
        this.picture = picture;
    }
}
