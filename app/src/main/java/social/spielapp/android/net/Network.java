package social.spielapp.android.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kotlin.NotImplementedError;
import okhttp3.*;
import social.spielapp.android.models.Channel;
import social.spielapp.android.models.Message;
import social.spielapp.android.models.Packet;

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

    public static void onMessageReceive(Message message) {
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

    public static void onReply(Packet packet) {

    }

    public static void createWebsocket() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("ws://localhost:8001")
                .build();

        webSocket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                Network.webSocket = null;
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
                try {
                    JSONObject data = new JSONObject(text);
                    switch (data.getString("path")) {
                        case "/v1/user/inbox":
                            onMessageReceive(Message.fromJsonObject(data.getJSONObject("message")));
                            break;
                        case "/v1/reply":
                            onReply(Packet.fromJsonObject(data));
                            break;
                    }
                } catch (JSONException e) {
                    Log.e(Network.class.getName(), "Invalid JSON data", e);
                }
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
