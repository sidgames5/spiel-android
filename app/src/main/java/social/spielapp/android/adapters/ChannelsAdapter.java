package social.spielapp.android.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

import social.spielapp.android.databinding.ItemContainerChannelBinding;
import social.spielapp.android.listeners.ChannelListener;
import social.spielapp.android.models.Channel;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {
    private final List<Channel> channels;

    private final ChannelListener channelListener;

    public ChannelsAdapter(List<Channel> channels, ChannelListener channelListener) {
        this.channels = channels;
        this.channelListener = channelListener;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerChannelBinding itemContainerChannelBinding = ItemContainerChannelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChannelViewHolder(itemContainerChannelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.setChannelData(channels.get(position));
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        ItemContainerChannelBinding binding;

        ChannelViewHolder(ItemContainerChannelBinding itemContainerChannelBinding) {
            super(itemContainerChannelBinding.getRoot());
            binding = itemContainerChannelBinding;
        }

        void setChannelData(Channel channel) {
            binding.textName.setText(channel.name);
            binding.imagePicture.setImageBitmap(getImage(channel.pictureBytes));
            binding.getRoot().setOnClickListener(v -> channelListener.onChannelClicked(channel));
        }
    }
    private Bitmap getImage(String picture) {
        byte[] bytes;
        if (picture != null) {
            bytes = picture.getBytes(Charset.defaultCharset());
        } else {
            bytes = "".getBytes(Charset.defaultCharset());
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
