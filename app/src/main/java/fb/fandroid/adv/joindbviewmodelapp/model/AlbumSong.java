package fb.fandroid.adv.joindbviewmodelapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by andrew on 27.05.2019.
 */
/*Создаем класс для связи Album & Song Many-to many relationship*/
@Entity(foreignKeys = {
        @ForeignKey(entity = Album.class, parentColumns = "id", childColumns = "album_id", onDelete = CASCADE, onUpdate = CASCADE),
        @ForeignKey(entity = Song.class, parentColumns = "id", childColumns = "song_id", onDelete = CASCADE, onUpdate = CASCADE)}
)

public class AlbumSong implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "album_id")
    @SerializedName("album_id")
    private int mAlbumId;

    @ColumnInfo(name = "song_id")
    @SerializedName("song_id")
    private int mSongId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(int albumId) {
        mAlbumId = albumId;
    }

    public int getSongId() {
        return mSongId;
    }

    public void setSongId(int songId) {
        mSongId = songId;
    }


}
