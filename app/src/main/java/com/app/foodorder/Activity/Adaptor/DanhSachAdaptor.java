package com.app.foodorder.Activity.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodorder.Activity.Domain.DanhSachDomain;
import com.app.foodorder.Activity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DanhSachAdaptor extends RecyclerView.Adapter<DanhSachAdaptor.ViewHolder> {
    ArrayList<DanhSachDomain> danhSachDomains;

    public DanhSachAdaptor(ArrayList<DanhSachDomain> danhSachDomains) {
        this.danhSachDomains = danhSachDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_danhsach,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.danhsachName.setText(danhSachDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:{
                picUrl="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background1));
            break;
            }
            case 1:{
                picUrl="cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background2));
            break;
            }
            case 2:{
                picUrl="cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background3));
            break;
            }
            case 3:{
                picUrl="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background4));
            break;
            }
            case 4:{
                picUrl="cat_5";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_background5));
            break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.danhsachPic);
    }

    @Override
    public int getItemCount() {
        return danhSachDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView danhsachName;
        ImageView danhsachPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            danhsachName=itemView.findViewById(R.id.danhsachName);
            danhsachPic=itemView.findViewById(R.id.danhsachPic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
