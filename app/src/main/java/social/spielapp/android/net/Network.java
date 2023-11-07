package social.spielapp.android.net;

import kotlin.NotImplementedError;
import okhttp3.*;

public class Network {
    public static void sendMessage(String message) {
        throw new NotImplementedError();
    }

    public static void onMessageReceive(String message) {
        throw new NotImplementedError();
    }

    public static void authenticate(String username, String password) {
        throw new NotImplementedError();
    }

    public static void onAuthSuccess(String token) {
        throw new NotImplementedError();
    }

    public static void onAuthError(String error) {
        throw new NotImplementedError();
    }
}
