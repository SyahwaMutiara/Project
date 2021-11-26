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

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ListModel> dataList;
    private List<ListModel> filteredDataList;
    private Context mContext;

    public Adapter(List<ListModel> list) {
        dataList = list;
        filteredDataList = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(filteredDataList.get(position).getNama());
        for (int i = 0; i < Math.round(filteredDataList.get(position).getRating()); i++) {
            holder.stars.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return (filteredDataList != null) ? filteredDataList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, location;
        private final LinearLayout stars;
        private final ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.cardview_title);
            location = itemView.findViewById(R.id.cardview_location);
            stars = itemView.findViewById(R.id.cardview_stars);
            photo = itemView.findViewById(R.id.cardview_photo);
        }
    }
}
