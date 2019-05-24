package fb.fandroid.adv.joindbviewmodelapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.database.Cursor;

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
    @Query("delete from album")
    void deleteAlbums();

/*
    @Delete
    void deleteAlbum(Album album);
*/

    //удалить альбом по id
    @Query("DELETE FROM album where id = :albumId")
    void deleteAlbumById(int albumId);

    //***************Song
    // К таблице Album
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSongs(List<Song> songs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSong(Song song);

    @Query("DELETE FROM song where id = :songId")
    int deleteSongById(int songId);

    //*******End Song

    //*****AlbumSong

/*
    @Query("select * from albumsong")
    List<AlbumSong> getAlbumSongs();

    @Query("select albumsong.id, album_id, album.name as name_album, song_id, song.name as name_song " +
            "from albumsong, song, album " +
            "where albumsong.song_id = song.id and albumsong.album_id = album.id " +
            "order by albumsong.id")
    Cursor getAlbumSongCursor();

    @Query("select albumsong.id, album_id, album.name as name_album, song_id, song.name as name_song " +
            "from albumsong, song, album " +
            "where albumsong.song_id = song.id and albumsong.album_id = album.id and albumsong.id = :albumsongId " +
            "order by albumsong.id")
    Cursor getAlbumSongWithIdCursor(int albumsongId);

    //получить список песен переданного id альбома
    @Query("select * from song inner join albumsong on song.id = albumsong.song_id where album_id = :albumId")
    List<Song> getSongsFromAlbum(int albumId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLinksAlbumSongs(List<AlbumSong> linksAlbumSongs);
*/

    //******EndAlbumSong



}
