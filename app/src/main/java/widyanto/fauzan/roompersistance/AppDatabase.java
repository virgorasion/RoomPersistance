package widyanto.fauzan.roompersistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by M Nur Fauzan W on 26-Aug-18.
 */

@Database(entities = {SiswaModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SiswaDAO userDao();
}
