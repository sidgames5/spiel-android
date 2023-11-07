package social.spielapp.android.net;

import kotlin.NotImplementedError;
import okhttp3.*;
import org.json.*;

public class Network {
    private static WebSocket webSocket;

    public static void sendMessage(String message, int channel) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("path", "/v1/channel/write");
        obj.put("message", new JSONObject().put("content", message));
        webSocket.send(obj.toString());
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

    public static void createWebsocket() {
        throw new NotImplementedError();
    }

    public static WebSocket getWebSocket() {
        return webSocket;
    }
}
