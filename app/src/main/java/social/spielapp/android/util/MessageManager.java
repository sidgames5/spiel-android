package social.spielapp.android.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import social.spielapp.android.models.Channel;
import social.spielapp.android.models.Message;

public class MessageManager {
    private static List<Message> messages = new ArrayList<>();
    private static Channel activeChannel = null;
    private static Consumer<Message> activeChannelConsumer;

    public static void addMessage(Message message) {
        messages.add(message);
        if (activeChannel.id == message.id && activeChannel != null) {
            activeChannelConsumer.accept(message);
        } else {
            NotificationManager.sendNotification(message.author.displayName, message.content);
        }
    }

    public static void editMessage(Message message) {
        // TODO
    }

    public static void deleteMessage(Message message) {
        messages.remove(message);
        // TODO notify the activity of a change
    }

    public static Message getMessage(int id, int channel) {
        for (Message message : messages) {
            if (message.channel == channel && message.id == id) return message;
        }
        return null;
    }

    public static List<Message> getMessages() {
        return messages;
    }

    public static Channel getActiveChannel() {
        return activeChannel;
    }

    public static Consumer<Message> getActiveChannelConsumer() {
        return activeChannelConsumer;
    }

    public static void setActiveChannel(Channel activeChannel, Consumer<Message> activeChannelConsumer) {
        MessageManager.activeChannel = activeChannel;
        MessageManager.activeChannelConsumer = activeChannelConsumer;
    }
}
