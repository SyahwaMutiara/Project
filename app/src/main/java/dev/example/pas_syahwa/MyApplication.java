package dev.example.pas_syahwa;
import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new  RealmConfiguration.Builder().allowWritesOnUiThread(true).name("movies.db").schemaVersion(0).build();
        Realm.setDefaultConfiguration(configuration);
    }
}