package com.ltd.tandung.amazon_shopping.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.GiohangActivity;
import com.ltd.tandung.amazon_shopping.activity.MainActivity;
import com.ltd.tandung.amazon_shopping.model.Giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.ltd.tandung.amazon_shopping.R.id.txtMuasau;
import static com.ltd.tandung.amazon_shopping.activity.GiohangActivity.txtThongBao;

/**
 * Created by letandung on 05/10/2017.
 */

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arrGiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arrGiohang) {
        this.context = context;
        this.arrGiohang = arrGiohang;
    }

    @Override
    public int getCount() {
        return arrGiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrGiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public TextView tv_tensp, tv_gia, txtSoluong, txtMuasau;
        public ImageView imgGiohang;
        public Button btn_giam, btn_tang;

    }

    public void deleteItem(int position) {
        MainActivity.mangGiohang.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customgiohang, null);
            viewHolder.tv_tensp = (TextView) view.findViewById(R.id.tv_tensp);
            viewHolder.tv_gia = (TextView) view.findViewById(R.id.tv_gia);
            viewHolder.imgGiohang = (ImageView) view.findViewById(R.id.imgGiohang);
            viewHolder.btn_giam = (Button) view.findViewById(R.id.btn_giam);
            viewHolder.btn_tang = (Button) view.findViewById(R.id.btn_tang);
            viewHolder.txtSoluong = (TextView) view.findViewById(R.id.txtSoluong);
            viewHolder.txtMuasau = (TextView) view.findViewById(txtMuasau);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Giohang giohang = (Giohang) getItem(position);
        viewHolder.tv_tensp.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tv_gia.setText("Giá: " + decimalFormat.format(giohang.getGiasp()) + " Đ");
        Picasso.with(context).load(giohang.getHinhanhsp()).placeholder(R.drawable.noimage).error(R.drawable.error).
                into(viewHolder.imgGiohang);
        viewHolder.txtSoluong.setText(giohang.getSoluongsp() + " ");
        ////////////////su kien  btn mua sau

        viewHolder.txtMuasau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.mangGiohang.size() <= 0) {
                            txtThongBao.setVisibility(View.VISIBLE);
                        } else {
                            deleteItem(position);
                            GiohangActivity.GetDuLieuLV();
                            if (MainActivity.mangGiohang.size() <= 0) {
                                txtThongBao.setVisibility(View.VISIBLE);
                            } else {
                                txtThongBao.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GiohangAdapter giohangAdapter = new GiohangAdapter(context, MainActivity.mangGiohang);
                        giohangAdapter.notifyDataSetChanged();
                        GiohangActivity.GetDuLieuLV();
                    }
                });
                builder.show();
            }
        });
        final int sl = Integer.parseInt(viewHolder.txtSoluong.getText().toString().trim());
        if (sl <= 1) {
            viewHolder.btn_giam.setVisibility(View.INVISIBLE);
        } else if (sl > 1) {
            viewHolder.btn_giam.setVisibility(View.VISIBLE);
            viewHolder.btn_tang.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        //su kien button tang
        viewHolder.btn_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.txtSoluong.getText().toString().trim()) + 1;
                int slhientai = MainActivity.mangGiohang.get(position).getSoluongsp();
                long giaht = MainActivity.mangGiohang.get(position).getGiasp();
                MainActivity.mangGiohang.get(position).setSoluongsp(slmoi);
                long giamoinhat = (giaht * slmoi) / slhientai;
                MainActivity.mangGiohang.get(position).setGiasp((int) giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                finalViewHolder.tv_gia.setText("Giá: " + decimalFormat.format(giamoinhat) + " Đ");
                GiohangActivity.GetDuLieuLV();
                finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                if (slmoi <= 1) {
                    finalViewHolder.btn_giam.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_tang.setVisibility(View.VISIBLE);
                    finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                } else if (slmoi > 1) {
                    finalViewHolder.btn_giam.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_tang.setVisibility(View.VISIBLE);
                    finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                }
            }
        });
        //su kien button giam
        viewHolder.btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.txtSoluong.getText().toString().trim()) - 1;
                int slhientai = MainActivity.mangGiohang.get(position).getSoluongsp();
                long giaht = MainActivity.mangGiohang.get(position).getGiasp();
                MainActivity.mangGiohang.get(position).setSoluongsp(slmoi);
                long giamoinhat = (giaht * slmoi) / slhientai;
                MainActivity.mangGiohang.get(position).setGiasp((int) giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                finalViewHolder.tv_gia.setText("Giá: " + decimalFormat.format(giamoinhat) + " Đ");
                GiohangActivity.GetDuLieuLV();
                finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                if (slmoi <= 1) {
                    finalViewHolder.btn_giam.setVisibility(View.INVISIBLE);
                    finalViewHolder.btn_tang.setVisibility(View.VISIBLE);
                    finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.btn_giam.setVisibility(View.VISIBLE);
                    finalViewHolder.btn_tang.setVisibility(View.VISIBLE);
                    finalViewHolder.txtSoluong.setText(String.valueOf(slmoi));
                }
            }
        });
        return view;
    }
}
