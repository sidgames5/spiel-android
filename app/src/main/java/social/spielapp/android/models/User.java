package social.spielapp.android.models;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

public class User extends Author implements Serializable {
    public final String passwordHash;

    public User(String username, String displayName, String picture, String passwordHash, int id) {
        super(username, displayName, picture, id);
        this.passwordHash = passwordHash;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "passwordHash='" + passwordHash + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", picture=" + pictureBytes +
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
