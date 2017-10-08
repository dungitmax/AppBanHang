package com.ltd.tandung.amazon_shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by letandung on 02/10/2017.
 */

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListLoaisp;
    Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListLoaisp, Context context) {
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLoaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txtLoaisp;
        ImageView imgLoaisp;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_loaisp, null);
            viewHolder.txtLoaisp = (TextView) view.findViewById(R.id.txtLoaisp);
            viewHolder.imgLoaisp = (ImageView) view.findViewById(R.id.imgLoaisp);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            Loaisp loaisp = (Loaisp) getItem(position);
            viewHolder.txtLoaisp.setText(loaisp.getTenloaisp());
            Picasso.with(context).load(loaisp.getHinhanhloaisp()).
                    placeholder(R.drawable.noimage)
                    .error(R.drawable.error).into(viewHolder.imgLoaisp);
        }

        return view;
    }
}
