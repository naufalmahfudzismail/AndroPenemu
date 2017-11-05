package com.example.android.project;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

class GridPenemuAdapter extends RecyclerView.Adapter<GridPenemuAdapter.GridViewHolder> {
    private Context context;
    private ArrayList<Penemu> listPenemu;

    public ArrayList<Penemu> getListPenemu() {
        return listPenemu;
    }

    public void setListPenemu(ArrayList<Penemu> listPenemu) {
        this.listPenemu = listPenemu;
    }

    public GridPenemuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_penemu, parent, false);
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        Glide.with(context)
                .load(getListPenemu().get(position).getPhoto())
                .override(350, 550)
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return getListPenemu().size();
    }
    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        public GridViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_item_photo);
        }

    }
}