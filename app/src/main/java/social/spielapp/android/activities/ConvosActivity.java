package social.spielapp.android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import social.spielapp.android.adapters.ChannelsAdapter;
import social.spielapp.android.databinding.ActivityConvosBinding;
import social.spielapp.android.listeners.ChannelListener;
import social.spielapp.android.util.SettingsManager;
import social.spielapp.android.util.types.Channel;

public class ConvosActivity extends AppCompatActivity implements ChannelListener {
    private ActivityConvosBinding binding;
    private SettingsManager settingsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConvosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        settingsManager = new SettingsManager(getApplicationContext());
        setListeners();
        getConvos();
    }

    private void getConvos() {
        loading(true);
        // handle shit
        List<Channel> channels = new ArrayList<>();
        Channel tchannel = new Channel(1, "Test", new ArrayList<>());
        channels.add(tchannel);
        if (channels.size() > 0) {
            ChannelsAdapter channelsAdapter = new ChannelsAdapter(channels, this);
            binding.channelsRecyclerView.setAdapter(channelsAdapter);
            binding.channelsRecyclerView.setVisibility(View.VISIBLE);
        } else {
            showErrorMessage();
        }
        loading(false);
    }

    private void showErrorMessage() {
        binding.textError.setText(String.format("%s", "No conversations"));
        binding.textError.setVisibility(View.VISIBLE);
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
        //binding.buttonNewChat.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), null)));
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onChannelClicked(Channel channel) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra("channel", channel);
        startActivity(intent);
        finish();
    }
}