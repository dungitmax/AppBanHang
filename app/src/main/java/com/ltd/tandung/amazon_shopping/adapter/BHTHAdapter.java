package com.ltd.tandung.amazon_shopping.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by letandung on 04/10/2017.
 */

public class BHTHAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Sanpham> listSanpham;

    public BHTHAdapter(Context context, int layout, List<Sanpham> listSanpham) {
        this.context = context;
        this.layout = layout;
        this.listSanpham = listSanpham;
    }
//    public void AddListItemAdapter(ArrayList<Sanpham> item){
//        listSanpham.addAll(item);
//        this.notifyDataSetChanged();
//    }
    @Override
    public int getCount() {
        return listSanpham.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        TextView txtTensanpham, txtGiasanpham, txtMotasp;
        ImageView imgSanPham;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            viewHolder.txtTensanpham = (TextView) view.findViewById(R.id.txtTensanpham);
            viewHolder.txtGiasanpham = (TextView) view.findViewById(R.id.txtGiasanpham);
            viewHolder.txtMotasp = (TextView) view.findViewById(R.id.txtMotasp);
            viewHolder.imgSanPham = (ImageView) view.findViewById(R.id.imgSanPham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = listSanpham.get(position);
        viewHolder.txtTensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiasanpham.setText("Giá: " + decimalFormat.format(sanpham.getGiasanpham()) + " Đ");
        viewHolder.txtMotasp.setMaxLines(2);
        viewHolder.txtMotasp.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotasp.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.noimage)
                .error(R.drawable.error).into(viewHolder.imgSanPham);
        return view;
    }
}
