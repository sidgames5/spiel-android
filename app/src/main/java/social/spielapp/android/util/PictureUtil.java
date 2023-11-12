package social.spielapp.android.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Base64;

public class PictureUtil {
    public static Bitmap getBitmap(String picture) {
        byte[] bytes = Base64.getDecoder().decode(picture);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
