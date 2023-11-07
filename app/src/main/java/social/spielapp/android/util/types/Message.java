package social.spielapp.android.util.types;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Message {
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
}
