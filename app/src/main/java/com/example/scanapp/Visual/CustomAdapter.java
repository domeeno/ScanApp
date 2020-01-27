package com.example.scanapp.Visual;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.scanapp.Functional.Dish;
import com.example.scanapp.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Dish> orders;

    public CustomAdapter(Context context, ArrayList<Dish> orders) {
        this.context = context;
        this.orders = orders;
        Log.i("ordersSize", orders.size()+"");
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        try {
            if(convertView == null)
            {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_view_layout_src, null);
            }

            TextView textView = convertView.findViewById(R.id.listViewText);
            textView.setText(orders.get(position).getDishName());

            TextView textView1 = convertView.findViewById(R.id.listViewPrice);
            textView1.setText(orders.get(position).getDishPrice());

            TextView textView2 = convertView.findViewById(R.id.listViewCount);
            textView2.setText(String.valueOf(orders.get(position).getDishCount()));

            int currentPrice;
            currentPrice = orders.get(position).getDishCount()*Integer.parseInt(orders.get(position).getDishPrice());

            TextView textView3 = convertView.findViewById(R.id.total_item_price);
            textView3.setText(String.valueOf(currentPrice));

        } catch (Exception e)
        {
            Log.i("someFailure", "Somethind didn't go as planned");
        }
        return convertView;
    }
}
