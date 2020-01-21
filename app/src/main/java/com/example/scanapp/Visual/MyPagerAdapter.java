package com.example.scanapp.Visual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.scanapp.R;

public class MyPagerAdapter extends PagerAdapter {

    private Context context;
    private int[] imageIDs = new int[] {R.drawable.background, R.drawable.background1, R.drawable.background2};

    public MyPagerAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return imageIDs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageIDs[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
