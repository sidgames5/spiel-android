package social.spielapp.android.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import kotlin.NotImplementedError;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import social.spielapp.android.models.Author;
import social.spielapp.android.models.Channel;
import social.spielapp.android.models.Message;
import social.spielapp.android.models.Packet;
import social.spielapp.android.util.MessageManager;

public class Network {
    private static WebSocket webSocket;
    private static Consumer<Message> messageReceiver;
    private static List<Packet> packetHistory;

    public static void setMessageReceiver(Consumer<Message> messageReceiver) {
        Network.messageReceiver = messageReceiver;
        Message m = new Message();
        m.content = "hi";
        m.uuid = UUID.randomUUID();
        m.timestamp = new Date().getTime();
        Author a = new Author();
        a.id = 2;
        a.pictureBytes = "";
        a.displayName = "Tester";
        a.username = "testing123";
        m.author = a;
        messageReceiver.accept(m);
    }

    public static void sendMessage(Message message, int channel) throws JSONException {
        if (webSocket != null) {
            JSONObject obj = new JSONObject();
            obj.put("path", "/v1/channel/write");
            obj.put("message", message.toJsonObject());
            webSocket.send(obj.toString());
        } else {
            Log.w(Network.class.getName(), "Websocket not initialized");
        }
    }

    public static void sendMessage(Message message, Channel channel) throws JSONException {
        sendMessage(message, channel.id);
    }

    public static void sendRaw(Packet packet) {
        webSocket.send(new JSONObject(packet.toMap()).toString());
    }

    public static void getMessageHistory(int channel, int start) throws JSONException {
        Packet packet = new Packet();
        packet.id = UUID.randomUUID();
        packet.path = "/v1/channel/read";
        JSONObject data = new JSONObject();
        data.put("channel", channel);
        data.put("amount", "range");
        data.put("start", start);
        packet.data = data;
        packet.status = 0;
        if (webSocket != null) {
            webSocket.send(new JSONObject(packet.toMap()).toString());
        } else {
            Log.w(Network.class.getName(), "Websocket not initialized");
        }
    }

    public static void getMessageHistory(int channel) throws JSONException {
        getMessageHistory(channel, 0);
    }

    public static void onMessageReceive(Message message) {
        MessageManager.addMessage(message);
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
