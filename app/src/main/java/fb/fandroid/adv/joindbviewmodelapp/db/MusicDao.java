package fb.fandroid.adv.joindbviewmodelapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fb.fandroid.adv.joindbviewmodelapp.model.Album;
import fb.fandroid.adv.joindbviewmodelapp.model.Comment;
import fb.fandroid.adv.joindbviewmodelapp.model.Song;

/**
 * @author Azret Magometov
 */

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);


    @Query("SELECT * from album ORDER by `release`")
    List<Album> getAlbums();

    //удалить альбом
    @Query("delete from album")
    void deleteAlbums();

    //удалить альбом по id
    @Query("DELETE FROM album where id = :albumId")
    void deleteAlbumById(int albumId);

    //***************Song
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Query("DELETE FROM song where id = :songId")
    int deleteSongById(int songId);

    @Query("SELECT * FROM song ORDER by id ")
    List<Song> getSongs();

    @Query("SELECT * FROM song WHERE album_id = :albumId ORDER by id ")
    List<Song> getSongsByAlbumId(int albumId);
    //*******End Song

    @Query("SELECT * from comment WHERE album_id = :albumId")
    List<Comment> getCommentsByAlbumId(int albumId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComments(List<Comment> comments);

    @Query("SELECT * from comment")
    List<Comment> getComments();



}
