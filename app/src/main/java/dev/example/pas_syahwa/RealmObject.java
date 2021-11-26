package dev.example.pas_syahwa;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

class RealmHelper {

    Realm realm;
    int id;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    // untuk menyimpan data
    public int save(final ListModel listModel) {
        id = 0;
        realm.executeTransaction(new Realm.Transaction() {
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
                    id = nextId;
                    listModel.setId(nextId);
                    ListModel model = realm.copyToRealm(listModel);
                } else {
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
        return listModel.getId();
    }

    // untuk memanggil semua data
    public List<ListModel> getAllMahasiswa() {
        RealmResults<ListModel> results = realm.where(ListModel.class).findAll();
        System.out.println("results name: "+results.get(0).getNama());
        System.out.println("results desc: "+results.get(0).getDeskripsi());
        return results;
    }

    // untuk menghapus data
    public void delete(Integer id) {
        final RealmResults<ListModel> model = realm.where(ListModel.class).equalTo("id", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }
}