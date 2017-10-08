package com.ltd.tandung.amazon_shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.ChitietsanphamActivity;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by letandung on 02/10/2017.
 */

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public SanphamAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sanphammoinhat, null);
        ItemHolder itemHolder = new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        Sanpham sanpham = sanphamArrayList.get(position);
        holder.txtTensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiasanpham())+" Đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).placeholder(R.drawable.noimage).error(R.drawable.error).
                into(holder.imgHinhsanpham);

    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgHinhsanpham;
        public TextView txtTensanpham, txtGiasanpham;

        public ItemHolder(View itemView) {
            super(itemView);
            imgHinhsanpham = (ImageView) itemView.findViewById(R.id.imgSanphammoi);
            txtTensanpham = (TextView) itemView.findViewById(R.id.txtTensanphammoi);
            txtGiasanpham = (TextView) itemView.findViewById(R.id.txtGiasanphammoi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent=new Intent(context, ChitietsanphamActivity.class);
                    intent.putExtra("thongtinsanpham",sanphamArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
