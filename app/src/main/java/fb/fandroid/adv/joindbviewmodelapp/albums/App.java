package fb.fandroid.adv.joindbviewmodelapp.albums;

import android.app.Application;
import android.arch.persistence.room.Room;

import fb.fandroid.adv.joindbviewmodelapp.db.DataBase;

/**
 * Created by andrew on 18.05.2019.
 */

public class App extends Application {

    private DataBase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = Room.databaseBuilder(getApplicationContext(), DataBase.class, "music_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public DataBase getDatabase() {
        return mDatabase;
    }
}
