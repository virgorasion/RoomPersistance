package widyanto.fauzan.roompersistance;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by M Nur Fauzan W on 26-Aug-18.
 */

@Dao
public interface SiswaDAO {

    @Query("SELECT * FROM SiswaModel ORDER BY id DESC")
    List<SiswaModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(SiswaModel siswaModels);

    @Delete
    void deleteUsers(SiswaModel siswaModel);

    @Update
    void update(SiswaModel siswaModel);
}
