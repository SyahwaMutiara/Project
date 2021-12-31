package dev.example.pas_syahwa;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {
    public static final String ITEM_EXTRA = "item_extra";
    private Bundle bundle;
    private TextView nama, deskripsi, rating;
    private ImageView img;
    private String name, desc, image;
    private float ratings;
    private boolean isFavorite;
    Button btn_fav;
    Realm realm;
    RealmHelper realmHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.poster_api);
        nama = findViewById(R.id.tv_judul);
        deskripsi = findViewById(R.id.tv_description);
        rating = findViewById(R.id.tv_rating);

//        RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
//        realm = Realm.getInstance(configuration);
//        realmHelper = new RealmHelper(realm);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("title");
            desc = bundle.getString("description");
            ratings = bundle.getFloat("rating");
            image = bundle.getString("image");
            isFavorite = bundle.getBoolean("favorite");


            id = bundle.getInt("id");

            nama.setText(name);
            deskripsi.setText(desc);
            rating.setText(String.valueOf(ratings));
            Glide.with(this).load("https://restaurant-api.dicoding.dev/images/medium/"+image).into(img);
        }
      if (getSupportActionBar() !=null){
          getSupportActionBar().setTitle("Detail Club");
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        if (isFavorite) {
//            btn_fav.setText("Remove from favorites");
//        } else {
//            btn_fav.setText("Add to favorites");
//        }

//        btn_fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isFavorite) {
////                    realmHelper.delete(id);
//                    btn_fav.setText("Add to favorites");
//                    isFavorite = false;
//                } else {
//                    ListModel model = new ListModel(name, image, desc, true, 5.5f);
////                    id = realmHelper.save(model);
//                    btn_fav.setText("Remove from favorites");
//                    isFavorite = true;
//                }
//                Toast.makeText(DetailActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent();
//        intent.putExtra("id", id);
//        intent.putExtra("isFavorite", isFavorite);
//        setResult(RESULT_OK, intent);
//        super.onBackPressed();
//    }
    }
}

