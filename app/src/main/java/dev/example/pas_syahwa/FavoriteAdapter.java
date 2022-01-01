package dev.example.pas_syahwa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    private Context mContext;
    public Callback callback;
    View viewku;
    Realm realm;
    private List<ListModel> list;
    public interface Callback {
        void onClick(int position);
    }
    public FavoriteAdapter( Context context, List<ListModel> userlist) {
        this.mContext = context;
        this.list = userlist;

    }
    RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getNama());
        holder.location.setText(list.get(position).getKota());
        holder.stars.setText(String.valueOf(list.get(position).getRating()));
        Glide.with(mContext).load("https://restaurant-api.dicoding.dev/images/medium/"+list.get(position).getGambar()).into(holder.photo);
        holder.posku = list.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView title, location;
        private final TextView stars;
        private final ImageView photo;
        private final RelativeLayout relativeLayout;
        Integer posku;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rv_click);
            title = itemView.findViewById(R.id.tv_judul_item);
            location = itemView.findViewById(R.id.tv_tanggal_item);
            stars = itemView.findViewById(R.id.rating);
            photo = itemView.findViewById(R.id.im_gambar_item);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onClick(getAdapterPosition());
                }
            });

        }   public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Delete = menu.add(Menu.NONE, 1, 1, "Hapus");
            Delete.setOnMenuItemClickListener(this::onMenuItemClick);
        }
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case 1:
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Apakah kamu mau menghapus item ini?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    realm = Realm.getInstance(configuration);
                                    RealmHelper realmHelper = new RealmHelper(realm);
                                    realmHelper.delete(posku);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            })
                            //Set your icon here
                            .setTitle("Hapus data");
                    AlertDialog alert = builder.create();
                    alert.show();//showing the dialog

                    break;
            }
            return true;
        }

    }
}
