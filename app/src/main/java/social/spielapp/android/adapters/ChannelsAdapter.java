package social.spielapp.android.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URI;
import java.util.Base64;
import java.util.List;

import social.spielapp.android.databinding.ItemContainerChannelBinding;
import social.spielapp.android.databinding.ItemContainerUserBinding;
import social.spielapp.android.util.types.Channel;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {
    private final List<Channel> channels;

    public ChannelsAdapter(List<Channel> channels) {
        this.channels = channels;
    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerChannelBinding itemContainerChannelBinding = ItemContainerChannelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChannelViewHolder(itemContainerChannelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.setUserData(channels.get(position));
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

        void setUserData(Channel channel) {
            binding.textName.setText(channel.name);
            binding.imagePicture.setImageBitmap(getUserImage(channel.picture));
        }
    }
    private Bitmap getUserImage(URI picture) {
        // TODO: download picture and handle shit
        String encodedImage = "";
        byte[] bytes = Base64.getDecoder().decode(encodedImage);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
