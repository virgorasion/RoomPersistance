package widyanto.fauzan.roompersistance;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by M Nur Fauzan W on 26-Aug-18.
 */

public class SiswaApp extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "siswa.db").allowMainThreadQueries().build();
    }
}
