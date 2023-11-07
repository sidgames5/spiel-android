package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import java.util.List;

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
}
