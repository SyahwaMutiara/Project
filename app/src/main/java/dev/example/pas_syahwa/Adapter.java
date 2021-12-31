package dev.example.pas_syahwa;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ListModel> list;
    private List<ListModel> filteredDataList;
    private Context mContext;
    Callback callback;
    public interface  Callback{
        void Call(int position);
    }

    public Adapter(Context mContext, List<ListModel> list , Callback callback ) {
        this.list = list;
        this.mContext = mContext;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getNama());
        holder.location.setText(list.get(position).getKota());
        holder.stars.setText(String.valueOf(list.get(position).getRating()));
        Glide.with(mContext).load("https://restaurant-api.dicoding.dev/images/medium/"+list.get(position).getGambar()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return (list != null) ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, location;
        private final TextView stars;
        private final ImageView photo;
        private final RelativeLayout relativeLayout;

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
                    callback.Call(getAdapterPosition());
                }
            });
        }
    }
}
