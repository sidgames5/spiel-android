package social.spielapp.android.net;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class SpielWebSocketClient extends WebSocketClient {
    public SpielWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.i(Network.class.getName(), "Websocket opened");

    }

    @Override
    public void onMessage(String message) {

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        //Network.webSocket = null;
        Log.d(Network.class.getName(), code + " " + reason + " " + remote);
    }

    @Override
    public void onError(Exception ex) {

    }
}
