package fb.fandroid.adv.joindbviewmodelapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fb.fandroid.adv.joindbviewmodelapp.model.Album;
import fb.fandroid.adv.joindbviewmodelapp.model.Song;

/**
 * @author Azret Magometov
 */

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);

    @Query("SELECT * from album")
    List<Album> getAlbums();

    //удалить альбом
    @Delete
    void deleteAlbum(Album album);

    //удалить альбом по id
    @Query("DELETE FROM album where id = :albumId")
    void deleteAlbumById(int albumId);

    //***************Song
    // К таблице Album отношение 1 ко многим
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Query("DELETE FROM song where id = :songId")
    int deleteSongById(int songId);

    //*******End Song





}
