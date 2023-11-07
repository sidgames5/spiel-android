package social.spielapp.android.util.types;

import java.net.URI;

public class User extends Author {
    public final String passwordHash;

    public User(String username, String displayName, URI picture, String passwordHash) {
        super(username, displayName, picture);
        this.passwordHash = passwordHash;
    }
}
