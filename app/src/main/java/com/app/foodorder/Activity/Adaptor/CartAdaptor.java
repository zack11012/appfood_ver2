package com.app.foodorder.Activity.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodorder.Activity.Domain.FoodDomain;
import com.app.foodorder.Activity.Helper.ManagementCart;
import com.app.foodorder.Activity.Interface.ChangeNumberItemsListener;
import com.app.foodorder.Activity.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdaptor(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachitem.setText(String.valueOf(foodDomains.get(position).getGia()));
        holder.totalEachitem.setText(String.valueOf(Math.round((foodDomains.get(position).getSoLuong() * foodDomains.get(position).getGia()) * 100) / 100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getSoLuong()));
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(foodDomains,position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
        holder.minusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachitem;
        ImageView pic, plusitem, minusitem;
        TextView totalEachitem, num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxt);
            feeEachitem = itemView.findViewById(R.id.feeEachitem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachitem = itemView.findViewById(R.id.totalEachitem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusitem = itemView.findViewById(R.id.plusCartBtn);
            minusitem = itemView.findViewById(R.id.minusCartBtn);

        }
    }
}
