package social.spielapp.android.models;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Author implements Serializable {
    public String username;
    public String displayName;
    public String pictureBytes;
    public int id;

    public Author(String username, String displayName, String pictureBytes, int id) {
        this.username = username;
        this.displayName = displayName;
        this.pictureBytes = pictureBytes;
        this.id = id;
    }

    public Author() {}

    public static Author fromJsonObject(JSONObject author) throws JSONException {
        return new Author(
                author.getString("username"),
                author.getString("displayName"),
                author.getString("picture"),
                author.getInt("id")
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", pictureBytes=" + pictureBytes +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("displayName", displayName);
        map.put("pictureBytes", pictureBytes);
        map.put("id", id);
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
