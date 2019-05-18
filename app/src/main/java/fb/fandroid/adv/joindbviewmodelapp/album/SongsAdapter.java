package fb.fandroid.adv.joindbviewmodelapp.album;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fb.fandroid.adv.joindbviewmodelapp.R;
import fb.fandroid.adv.joindbviewmodelapp.model.Song;

public class SongsAdapter extends RecyclerView.Adapter<SongsHolder> {

    @NonNull
    private final List<Song> mSongs = new ArrayList<>();

    @Override
    public SongsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_song, parent, false);
        return new SongsHolder(view);
    }

    @Override
    public void onBindViewHolder(SongsHolder holder, int position) {
        Song song = mSongs.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    public void addData(List<Song> data, boolean isRefreshed) {
        if (isRefreshed) {
            mSongs.clear();
        }

        mSongs.addAll(data);
        notifyDataSetChanged();
    }
}
