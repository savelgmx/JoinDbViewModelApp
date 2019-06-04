package fb.fandroid.adv.joindbviewmodelapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity
public class Comment implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int mId;

    @ColumnInfo(name = "album_id")
    @SerializedName("album_id")
    private int mAlbumId;

    @ColumnInfo(name = "text")
    @SerializedName("text")
    private String mText;

    @ColumnInfo(name = "author")
    @SerializedName("author")
    private String mAuthor;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    private String mTimestamp;

    public Comment(){};

    public Comment(int mId, int mAlbumId, String mText, String mAuthor, String mTimestamp) {
        this.mId = mId;
        this.mAlbumId = mAlbumId;
        this.mText = mText;
        this.mAuthor = mAuthor;
        this.mTimestamp = mTimestamp;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(int mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
