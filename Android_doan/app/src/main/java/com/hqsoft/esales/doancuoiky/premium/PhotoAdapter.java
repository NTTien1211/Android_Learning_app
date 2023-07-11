package com.hqsoft.esales.doancuoiky.premium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hqsoft.esales.doancuoiky.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.Photoviewhoder>{
    private final List<Photo> list;

    public PhotoAdapter(List<Photo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Photoviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_photo, parent, false);

        return new Photoviewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Photoviewhoder holder, int position) {
        Photo photo = list.get(position);
        if (photo==null){
            return;
        }
        holder.imageView.setImageResource(photo.getResourceID());
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public static class Photoviewhoder extends RecyclerView.ViewHolder{
        private final GifImageView imageView;
        public Photoviewhoder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo);
        }
    }

}
