package com.example.scanapp.Visual;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scanapp.Activity.DishInformationActivity;
import com.example.scanapp.Activity.MenuActivity;
import com.example.scanapp.Functional.Category;
import com.example.scanapp.Functional.Dish;
import com.example.scanapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpandedListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Category> dishCategory;
    private ArrayList<Dish> orders;
    private Map<Category, List<Dish>> dishes;
    public static Dish dishCurrentInfo;

    public ExpandedListAdapter(Context context, ArrayList<Category> dishCategory, Map<Category, List<Dish>> dishes, ArrayList<Dish> orders) {
        this.context = context;
        this.dishCategory = dishCategory;
        this.orders = orders;
        this.dishes = dishes;
    }

    @Override
    public int getGroupCount() {
        return dishCategory.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dishes.get(dishCategory.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dishCategory.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dishes.get(dishCategory.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Category dishCategory = (Category) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }

        TextView txtGroup = convertView.findViewById(R.id.groupText);
        ImageView imageView = convertView.findViewById(R.id.groupImage);

        txtGroup.setText(dishCategory.getCategoryName());

//        byte[] decodedString = Base64.decode(dishCategory.getCategoryImage(), Base64.DEFAULT);
//        imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        switch (dishCategory.getCategoryName()) {
            case "Special Offers":
                imageView.setImageResource(R.drawable.specials);
                break;
            case "Main Dishes":
                imageView.setImageResource(R.drawable.main);
                break;
            case "Pizza":
                imageView.setImageResource(R.drawable.pizza);
                break;
            case "Salads":
                imageView.setImageResource(R.drawable.salads);
                break;
            case "Desserts":
                imageView.setImageResource(R.drawable.deserts);
                break;
            case "Soups":
                imageView.setImageResource(R.drawable.soups);
                break;
            case "Hot Drinks":
                imageView.setImageResource(R.drawable.hotdrinks);
                break;
            case "Beverages":
                imageView.setImageResource(R.drawable.beverages);
                break;
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Dish dish = (Dish) getChild(groupPosition, childPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_element, null);
        }

        TextView txtElement = convertView.findViewById(R.id.listText);
        TextView txtElement2 = convertView.findViewById(R.id.listPrice);
 //       ImageView imageView = convertView.findViewById(R.id.listImage);

        Button plus = convertView.findViewById(R.id.addButton);
        Button minus = convertView.findViewById(R.id.removeButton);
        Button info = convertView.findViewById(R.id.show_dish_info);
//
        txtElement.setText(dish.getDishName());
        txtElement2.setText(dish.getDishPrice());

//        byte[] decodedString = Base64.decode(dish.getDishImage(), Base64.DEFAULT);
//        imageView.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));

        Log.i("h", "size of orders:" + orders.size());
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dish f = new Dish(dish.getDishName(), dish.getDishPrice());

                boolean inList = false;

                if(orders.size() == 0){
                    orders.add(f);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, f.getDishName() + "added to List", duration);
                    toast.show();
                }
                else {
                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getDishName().equals(f.getDishName())) {
                            orders.get(i).setDishCount(orders.get(i).getDishCount() + 1);
                            inList = true;
                        }
                    }
                }

                if(inList){
                    Log.i("inList", "True");
                } else {
                    Log.i("inList", "False");
                    if(orders.size() == 1){
                        orders.remove(f);
                    }
                    orders.add(f);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, f.getDishName() + " was added to your List", duration);
                    toast.show();
                }


                for (int i = 0; i < orders.size(); i++) {
                    Log.i(i+": ", orders.get(i).getDishName());
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toRemove = 0;
                boolean isDeletable = false;
                try {
                    for (int i = 0; i < orders.size(); i++) {
                        if (dishes.get(dishCategory.get(groupPosition)).get(childPosition).getDishName().equals(orders.get(i).getDishName())) {
                            toRemove = i;
                            Log.i("Info", "i=" + i);
                            isDeletable = true;
                            break;
                        }
                    }

                    if (isDeletable) {
                        Log.i("Info", "toRemove=" + toRemove);
                        if(orders.get(toRemove).getDishCount() > 1){
                            Log.i("Info", "About to remove one of the" + orders.get(toRemove));
                            orders.get(toRemove).setDishCount(orders.get(toRemove).getDishCount()-1);
                        } else {
                            Log.i("Info", "About to remove:" + orders.get(toRemove));

                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, orders.get(toRemove).getDishName() + " was removed from your List", duration);
                            toast.show();

                            orders.remove(toRemove);
                        }
                    } else {
                        Log.i("elementNotFound", "Nothing to Delete");
                    }
                } catch (Exception e){
                    Log.i("wrong", "extremely wrong");
                }
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dishCurrentInfo = new Dish(dish.getDishName(), dish.getDishPrice());
                Intent intent = new Intent(context, DishInformationActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
