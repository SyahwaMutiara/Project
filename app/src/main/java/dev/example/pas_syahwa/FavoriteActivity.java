package dev.example.pas_syahwa;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteActivity extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    List<ListModel> modelNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Setup Realm
        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        realm = Realm.getInstance(configuration);

        realmHelper = new RealmHelper(realm);
        modelNews = new ArrayList<>();

        modelNews = realmHelper.getAllMovies();

        show();
    }

    protected void onRestart() {
        super.onRestart();
        favoriteAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        favoriteAdapter = new FavoriteAdapter(this, modelNews);
        recyclerView.setAdapter(favoriteAdapter);
    }
}
