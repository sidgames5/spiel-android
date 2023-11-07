package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Author {
    public final String username;
    public final String displayName;
    public final URI picture;

    public Author(String username, String displayName, URI picture) {
        this.username = username;
        this.displayName = displayName;
        this.picture = picture;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", picture=" + picture +
                '}';
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("displayName", displayName);
        map.put("picture", picture.toString());
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
