package social.spielapp.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import social.spielapp.android.databinding.ActivityChatBinding;
import social.spielapp.android.models.Channel;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private Channel receiverChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ConvosActivity.class)));
    }

    private void loadReceiverDetails() {
        receiverChannel = (Channel) getIntent().getSerializableExtra("channel");
        assert receiverChannel != null;
        binding.textName.setText(receiverChannel.name);
    }
}