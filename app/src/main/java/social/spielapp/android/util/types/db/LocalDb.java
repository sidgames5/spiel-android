package social.spielapp.android.util.types.db;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import social.spielapp.android.util.types.Settings;
import social.spielapp.android.util.types.User;

public class LocalDb {
    public User user;
    public List<UUID> readMessages;
    public Settings settings;

    public static LocalDb fromJsonObject(JSONObject author) throws JSONException {
        return new LocalDb();
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}
