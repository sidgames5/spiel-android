package social.spielapp.android.net

import org.json.JSONObject
import social.spielapp.android.models.Channel
import social.spielapp.android.models.Message
import social.spielapp.android.models.Packet
import java.util.UUID

class MessageHistory {
    companion object {
        @JvmStatic
        fun getLatestMessage(channel:Int):Message {

            var packet = Packet()
            packet.id = UUID.randomUUID()
            packet.status = 0;
            packet.path = "/v1/channel/read"
            var packetData = JSONObject()
            packetData.put("channel", channel)
            packetData.put("start", -1)
            packet.data = packetData

            Network.sendRaw(packet)

            return Network.receive(packet.id).data as Message
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