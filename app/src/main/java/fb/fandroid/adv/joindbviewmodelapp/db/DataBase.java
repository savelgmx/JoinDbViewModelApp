package fb.fandroid.adv.joindbviewmodelapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fb.fandroid.adv.joindbviewmodelapp.model.Album;


@Database(entities = {Album.class}, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract MusicDao getMusicDao();
}
