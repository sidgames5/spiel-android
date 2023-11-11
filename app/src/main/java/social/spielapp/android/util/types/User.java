package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("passwordHash", passwordHash);
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
