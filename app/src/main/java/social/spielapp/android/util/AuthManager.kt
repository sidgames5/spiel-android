package social.spielapp.android.util

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import social.spielapp.android.activities.MainActivity
import social.spielapp.android.models.Packet
import social.spielapp.android.models.User
import social.spielapp.android.net.Network
import java.util.UUID

class AuthManager {
    companion object {
        private var currentUser: User? = null

        /**
         * Log in to the server
         * @param username Username to log in as
         * @param password Password for the user
         * @return 0 Success, 1 No user, 2 Wrong pass, 3 Failed to connect
         */
        @JvmStatic
        fun login(username: String, password: String): Int {
            val packet = Packet()
            packet.id = UUID.randomUUID()
            packet.path = "/v1/auth/login"
            packet.status = 0
            packet.data = JSONObject().put("username", username).put("password", password)
            Network.sendRaw(packet)
            return 3
        }

        @JvmStatic
        fun logout(from: AppCompatActivity) {
            if (currentUser != null) {
                currentUser = null
                from.startActivity(Intent(from.applicationContext, MainActivity::class.java))
            } else {
                Log.w(AuthManager::class.java.name, "Not logged in")
            }
        }

        /**
         * Register a new account
         * @param username Username to register as
         * @param password Password for the user
         * @return 0 Success, 1 User exists, 2 Failed to connect
         */
        @JvmStatic
        fun register(username: String, password: String): Int {
            // TODO
            return 2
        }

        @JvmStatic
        fun isLoggedIn(): Boolean {
            return currentUser != null
        }

        @JvmStatic
        fun getCurrentUser(): User {
            return currentUser ?: User("testing123", "Tester", "", "", 1)
        }
    }
}