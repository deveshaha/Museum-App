package com.example.proyectob_pmdm_t2_devesh_hanumante.rvutils;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectob_pmdm_t2_devesh_hanumante.MuseumDetailed;
import com.example.proyectob_pmdm_t2_devesh_hanumante.R;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListVH> implements View.OnClickListener{

     private List<Museum> museums;
     private View.OnClickListener listener;

    public ListAdapter(List<Museum> museums){
        this.museums = museums;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListVH holder, int position) {
        Museum museum = museums.get(position);
        holder.bindMuseum(museum);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String museumName = museum.getTitle();
                String museumId = museum.getId();

                museumId = museumId.substring(museumId.lastIndexOf("/") + 1);

                System.out.println("Museum Name: " + museumName);
                System.out.println("Museum ID: " + museumId);

                Intent intent = new Intent(view.getContext(), MuseumDetailed.class);
                intent.putExtra("museumId", museumId);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return museums.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public static class ListVH extends RecyclerView.ViewHolder{
        TextView tvMuseumName;

        public ListVH(@NonNull View itemView) {
            super(itemView);
            tvMuseumName = itemView.findViewById(R.id.tv_museum_name);
        }

        public void bindMuseum(Museum museum){
            tvMuseumName.setText(museum.getTitle());
        }
    }

}
