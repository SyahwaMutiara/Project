package dev.example.pas_syahwa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ListModel> list;
    Adapter adapter;

    String gambar, deskripsi, nama, kota;
    float rating;
    private final int REQUEST_CODE = 101;
    private int listIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rv_home);
        recyclerView.setHasFixedSize(true);
        showRecylerList();
    }

    private void showRecylerList() {
        list = new ArrayList<>();
        AndroidNetworking.get("https://restaurant-api.dicoding.dev/list" )
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        {
                            try {
                                JSONArray results = response.getJSONArray("restaurants");
                                for (int i = 0; i < results.length(); i++) {
                                    JSONObject object = results.getJSONObject(i);
                                    Log.d("a","a"+object);
                                    kota =  object.getString("city");
                                    gambar =  object.getString("pictureId");
                                    nama  =  object.getString("name");
                                    deskripsi =  object.getString("description");
                                    rating = (float) object.getDouble("rating");
                                    list.add(new ListModel(i, kota, nama , gambar , deskripsi,  rating, false));
                                    Realm realm;
                                    ListModel favoriteClub;
                                }
                                adapter = new Adapter(MainActivity.this, list, new Adapter.Callback() {
                                    @Override
                                    public void Call(int position) {
                                        ListModel model = list.get(position);
                                        Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                                        i.putExtra("kota", model.getKota());
                                        i.putExtra("title", model.getNama());
                                        i.putExtra("description", model.getDeskripsi());
                                        i.putExtra("image", model.getGambar());
                                        i.putExtra("rating", model.getRating());
                                        i.putExtra("id", model.getId());
                                        startActivity(i);
                                    }
                                });
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }@Override
                    public void onError(ANError error) {
                        Log.d( "", "onError: " + error);
                    }

                });

    }

    private void setAdapter() {
        Adapter.Callback callback = new Adapter.Callback() {
            @Override
            public void Call(int position) {

            }
        };

        Adapter adapter = new Adapter(this, list, callback);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            list.get(listIndex).setId(data.getIntExtra("id", 0));
            list.get(listIndex).setFavorite(data.getBooleanExtra("isFavorite", false));
//            setAdapter();
            listIndex = 0;
        }
    }
}