package social.spielapp.android.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kotlin.NotImplementedError;
import okhttp3.*;
import okio.ByteString;
import social.spielapp.android.util.types.Channel;
import social.spielapp.android.util.types.Message;

import org.json.*;

public class Network {
    private static WebSocket webSocket;

    public static void sendMessage(Message message, int channel) throws JSONException {
        if (webSocket != null) {
            JSONObject obj = new JSONObject();
            obj.put("path", "/v1/channel/write");
            obj.put("message", message.toJsonObject());
            webSocket.send(obj.toString());
        } else {
            throw new IllegalStateException("Websocket not yet initialized");
        }
    }

    public static void sendMessage(Message message, Channel channel) throws JSONException {
        sendMessage(message, channel.id);
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
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("ws://localhost:8001")
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                super.onMessage(webSocket, text);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                super.onOpen(webSocket, response);
            }
        });
    }

    public static WebSocket getWebSocket() {
        return webSocket;
    }
}
