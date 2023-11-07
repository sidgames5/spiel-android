package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import java.net.URI;

public class User extends Author {
    public final String passwordHash;

    public User(String username, String displayName, URI picture, String passwordHash) {
        super(username, displayName, picture);
        this.passwordHash = passwordHash;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "passwordHash='" + passwordHash + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", picture=" + picture +
                '}';
    }
}
