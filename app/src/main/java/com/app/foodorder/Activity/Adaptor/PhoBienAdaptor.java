package com.app.foodorder.Activity.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodorder.Activity.DetailActivity;
import com.app.foodorder.Activity.Domain.DanhSachDomain;
import com.app.foodorder.Activity.Domain.FoodDomain;
import com.app.foodorder.Activity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PhoBienAdaptor extends RecyclerView.Adapter<PhoBienAdaptor.ViewHolder> {
    ArrayList<FoodDomain> popularFood;

    public PhoBienAdaptor(ArrayList<FoodDomain> danhSachFood) {
        this.popularFood = danhSachFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_phobien,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoBienAdaptor.ViewHolder holder, int position) {
        holder.title.setText(popularFood.get(position).getTitle());
        holder.gia.setText(String.valueOf(popularFood.get(position).getGia()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", popularFood.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, gia;
        ImageView pic;
        TextView btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title1);
            gia = itemView.findViewById(R.id.gia);
            pic = itemView.findViewById(R.id.pic);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }
}
