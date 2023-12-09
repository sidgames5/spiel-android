package social.spielapp.android.net

import social.spielapp.android.models.Channel
import social.spielapp.android.models.Message

class MessageHistory {
    companion object {
        @JvmStatic
        fun getLatestMessage(channel:Int):Message {
            TODO()
        }

        @JvmStatic
        fun getLatestMessages(channel:Int, count:Int):List<Message> {
            TODO()
        }

        @JvmStatic
        fun getMessageRange(channel: Int, start:Int, end:Int):List<Message> {
            TODO()
        }

        @JvmStatic
        fun getAllMessages(channel: Int):List<Message> {
            TODO()
        }
    }
}