package local.isaac.tt_2018_a031.configbd;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.fstyle.library.helper.AssetSQLiteOpenHelperFactory;

public class AppDatabase {

    private static AppDatabase ourInstance;
    private AppRepository appRepository;

    public static AppDatabase getInstance(Context context){
        if(ourInstance == null){
            ourInstance = new AppDatabase(context);
        }
        return ourInstance;
    }

    private AppDatabase(Context context){
        appRepository = Room.databaseBuilder(context,
                AppRepository.class,
                "eki_pasajero.db")
                .openHelperFactory(new AssetSQLiteOpenHelperFactory())
                .fallbackToDestructiveMigration()
                .build();
    }

    public AppRepository getAppRepository() {
        return appRepository;
    }
}
