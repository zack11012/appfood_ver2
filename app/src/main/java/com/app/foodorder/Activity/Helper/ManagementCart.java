package com.app.foodorder.Activity.Helper;

import android.content.Context;
import android.widget.Toast;

import com.app.foodorder.Activity.Domain.FoodDomain;
import com.app.foodorder.Activity.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getlistCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++){
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setSoLuong(item.getSoLuong());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();
    }
    public ArrayList<FoodDomain> getlistCart(){
         return tinyDB.getListObject("CartList");
    }
    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setSoLuong(listFood.get(position).getSoLuong()+1);
        tinyDB.putListObject("CartList",listFood);
        changeNumberItemsListener.change();
    }
    public void minusNumberFood(ArrayList<FoodDomain> listood,int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listood.get(position).getSoLuong()==1){
            listood.remove(position);
        }else {
            listood.get(position).setSoLuong(listood.get(position).getSoLuong()-1);
        }
        tinyDB.putListObject("CartList",listood);
        changeNumberItemsListener.change();
    }
    public Double getTotalFee(){
        ArrayList<FoodDomain> listfood = getlistCart();
        double fee =0;
        for (int i = 0;i < listfood.size(); i++){
            fee = fee+(listfood.get(i).getGia()*listfood.get(i).getSoLuong());
        }
        return fee;
    }
}
