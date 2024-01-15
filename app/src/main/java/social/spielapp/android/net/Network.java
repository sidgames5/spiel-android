package social.spielapp.android.net;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import kotlin.NotImplementedError;
import social.spielapp.android.models.Author;
import social.spielapp.android.models.Channel;
import social.spielapp.android.models.Message;
import social.spielapp.android.models.Packet;
import social.spielapp.android.util.MessageManager;

public class Network {
    static WebSocketClient webSocket;
    static SpielHttpClient httpClient;
    private static Consumer<Message> messageReceiver;
    private static final Map<UUID, Packet> receivedPackets = new HashMap<>();

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
        if (webSocket != null) {
            Gson gson = new Gson();
            String json = gson.toJson(packet);
            Log.d(Network.class.getName(), json);
            webSocket.send(json);
        } else {
            Log.w(Network.class.getName(), "Websocket not initialized");
        }
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

    public static void createClient() {
        Log.i(Network.class.getName(), "Creating HTTP client");

        URL serverUrl;

        try {
            serverUrl = new URL("http://localhost:8192");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        httpClient = new SpielHttpClient(serverUrl);
    }

    public static SpielHttpClient getClient() {
        return httpClient;
    }

    public static Packet receive(UUID id) {
        try {
            synchronized (receivedPackets) {
                while (!receivedPackets.containsKey(id)) {
                    receivedPackets.wait(500);
                    Log.i(Network.class.getName(), "Waiting for receive packet");
                }
                return receivedPackets.get(id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
