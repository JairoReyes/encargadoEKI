package local.isaac.tt_2018_a031.configbd;

import android.app.Application;
import android.content.Context;

public class InitBD extends Application {

    private static Context contextApplication;
    AppDatabase appDatabase;

    public void onCreate(){
        super.onCreate();
        contextApplication = getApplicationContext();
        appDatabase = AppDatabase.getInstance(getContextApplication());
    }

    public static Context getContextApplication() {
        return contextApplication;
    }
}
