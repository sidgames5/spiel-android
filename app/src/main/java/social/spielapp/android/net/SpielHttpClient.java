package social.spielapp.android.net;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpielHttpClient {
    private final URL url;

    public String send(String data, String mime) {
        try {

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", mime);
            connection.connect();

            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.writeBytes(data);
            dos.flush();
            dos.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            connection.disconnect();
            return response.toString();

        } catch (IOException e) {
            Log.e("SpielHttpClient", "Error sending data", e);
        }
        return null;
    }

    public URL getUrl() {
        return url;
    }

    public SpielHttpClient(URL url) {
        this.url = url;
    }
}
