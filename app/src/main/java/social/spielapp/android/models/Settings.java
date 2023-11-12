package social.spielapp.android.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Settings implements Serializable {
    public static Settings fromJsonObject(JSONObject author) throws JSONException {
        return new Settings();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
