package social.spielapp.android.models;

import java.io.Serializable;

public class DatabasePacket implements Serializable {
    public String instruction;
    public Object data1;
    public Object data2;
    public String token;

    public DatabasePacket() {}
}
