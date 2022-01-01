package dev.example.pas_syahwa;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {
    Realm realm;


    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void save(final ListModel listModel) {
        realm.executeTransaction( new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null) {
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(ListModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    listModel.setId(nextId);
                    ListModel itemModel = realm.copyToRealm(listModel);
                    final RealmResults<ListModel> item = realm.where(ListModel.class).findAll();
                } else {
                    Log.e("Log", "execute: Database not Exist");
                }
            }
        });
    }


    public List<ListModel> getAllMovies() {
        RealmResults<ListModel> results = realm.where(ListModel.class).findAll();
        return results;
    }
//    public boolean getAllMoviesByName(String name) {
//        boolean cek = false;
//        Model results = realm.where(Model.class).equalTo("judul", name).findFirst();
//        realm.beginTransaction();
//        if (results.getJudul() == null){
//            cek=true;
//        }
//        return cek;
//    }

    public void delete(Integer id){
        final RealmResults<ListModel> modelNews = realm.where(ListModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modelNews.deleteFromRealm(0);
            }
        });
    }

}
