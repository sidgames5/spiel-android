package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    public static Settings fromJsonObject(JSONObject author) throws JSONException {
        return new Settings();
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
