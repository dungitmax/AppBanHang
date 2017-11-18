package com.ltd.tandung.amazon_shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.DoDTActivity;
import com.ltd.tandung.amazon_shopping.activity.LD_SKActivity;
import com.ltd.tandung.amazon_shopping.activity.SachActivity;

/**
 * Created by letandung on 19/10/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide6};

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_viewpager, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == 0) {
                    Intent intent = new Intent(context, SachActivity.class);
                    context.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(context, LD_SKActivity.class);
                    context.startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(context, LD_SKActivity.class);
                    context.startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(context, LD_SKActivity.class);
                    context.startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(context, SachActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DoDTActivity.class);
                    context.startActivity(intent);
                }

            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
