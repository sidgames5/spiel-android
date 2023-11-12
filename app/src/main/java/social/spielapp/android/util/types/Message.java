package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Message implements Serializable {
    public final String content;
    public final Author author;
    public final int timestamp;
    public final UUID uuid;

    public Message(String content, Author author, int timestamp) {
        this.content = content;
        this.author = author;
        this.timestamp = timestamp;
        this.uuid = UUID.randomUUID();
        // TODO: add crc32
    }

    public static Message fromJsonObject(JSONObject message) throws JSONException {
        return new Message(
                message.getString("content"),
                Author.fromJsonObject(message.getJSONObject("author")),
                message.getInt("timestamp")
        );
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "content='" + content + '\'' +
                ", author=" + author +
                ", timestamp=" + timestamp +
                ", uuid=" + uuid +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("author", author);
        map.put("timestamp", timestamp);
        map.put("uuid", uuid.toString());
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
