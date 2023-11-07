package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Channel {
    public final int id;
    public final String name;
    public final List<Author> authors;

    public Channel(int id, String name, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                '}';
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("name", name);
        map.put("authors", authors.toString());
        return map;
    }

    public JSONObject toJsonObject() {
        return new JSONObject(this.toMap());
    }
}