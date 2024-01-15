package social.spielapp.android.models;

import java.io.Serializable;

public class AuthPacket implements Serializable {
    public boolean register;
    public String username;
    public String passwordHash;
    public String phone;

    public AuthPacket() {}
}
