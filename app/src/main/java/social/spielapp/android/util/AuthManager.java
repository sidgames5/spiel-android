package social.spielapp.android.util;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import social.spielapp.android.activities.WelcomeActivity;
import social.spielapp.android.util.types.User;

public class AuthManager {
    private static User currentUser = null;

    /**
     * Log in to the server
     * @param username Username to log in as
     * @param password Password for the user
     * @return 0 Success, 1 No user, 2 Wrong pass, 3 Failed to connect
     */
    public static int login(String username, String password) {
        // TODO
        return 3;
    }

    public static void logout(AppCompatActivity from) {
        if (currentUser != null) {
            currentUser = null;
            from.startActivity(new Intent(from.getApplicationContext(), WelcomeActivity.class));
        } else {
            Log.w(AuthManager.class.getName(), "Not logged in");
        }
    }

    /**
     * Register a new account
     * @param username Username to register as
     * @param password Password for the user
     * @return 0 Success, 1 User exists, 2 Failed to connect
     */
    public static int register(String username, String password) {
        // TODO
        return 2;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
