package social.spielapp.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import social.spielapp.android.adapters.ChatAdapter;
import social.spielapp.android.databinding.ActivityChatBinding;
import social.spielapp.android.models.Author;
import social.spielapp.android.models.Channel;
import social.spielapp.android.models.Message;
import social.spielapp.android.net.Network;
import social.spielapp.android.util.AuthManager;
import social.spielapp.android.util.MessageManager;
import social.spielapp.android.util.SettingsManager;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private Channel receiverChannel;
    private List<Message> messages;
    private ChatAdapter chatAdapter;
    private SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
        init();
    }

    private void init() {
        settingsManager = new SettingsManager(getApplicationContext());
        messages = new ArrayList<>();
        chatAdapter = new ChatAdapter(messages);
        binding.chatRecyclerView.setAdapter(chatAdapter);
        MessageManager.setActiveChannel(receiverChannel, this::writeMessage);
        try {
            Network.getMessageHistory(receiverChannel.id);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage() {
        Message message = new Message();
        message.author = (Author) AuthManager.getCurrentUser();
        message.uuid = UUID.randomUUID();
        message.timestamp = new Date().getTime();
        message.content = binding.inputMessage.getText().toString();
        try {
            Network.sendMessage(message, receiverChannel.id);
            messages.add(message);
            chatAdapter.notifyItemRangeInserted(messages.size(), messages.size());
            binding.chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
            binding.inputMessage.setText("");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMessage(Message message) {
        messages.add(message);
        chatAdapter.notifyItemRangeInserted(messages.size(), messages.size());
        binding.chatRecyclerView.smoothScrollToPosition(messages.size() - 1);
        binding.chatRecyclerView.setVisibility(View.VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ConvosActivity.class)));
        binding.layoutSend.setOnClickListener(v -> sendMessage());
    }

    private void loadReceiverDetails() {
        receiverChannel = (Channel) getIntent().getSerializableExtra("channel");
        assert receiverChannel != null;
        binding.textName.setText(receiverChannel.name);
    }
}