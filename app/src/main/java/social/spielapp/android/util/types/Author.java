package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONException;
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

    public static Author fromJsonObject(JSONObject author) throws JSONException {
        return new Author(
                author.getString("username"),
                author.getString("displayName"),
                URI.create(author.getString("picture"))
        );
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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("displayName", displayName);
        map.put("picture", picture);
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
