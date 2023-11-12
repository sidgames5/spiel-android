package social.spielapp.android.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Packet implements Serializable {
    public final int status;
    public final Object data;
    public final String path;
    public final UUID id;

    public Packet(int status, Object data, String path, UUID id) {
        this.status = status;
        this.data = data;
        this.path = path;
        this.id = id;
    }

    public Packet(Object data, String path, UUID id) {
        this.data = data;
        this.path = path;
        this.id = id;
        this.status = 0;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("path", path);
        map.put("id", id.toString());
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }

    public static Packet fromJsonObject(JSONObject json) throws JSONException {
        return new Packet(json.getInt("status"), json.get("data"), json.getString("path"), UUID.fromString(json.getString("id")));
    }
}
