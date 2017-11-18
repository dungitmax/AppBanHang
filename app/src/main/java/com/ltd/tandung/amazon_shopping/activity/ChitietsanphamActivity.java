package com.ltd.tandung.amazon_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.model.Giohang;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietsanphamActivity extends AppCompatActivity {
    Toolbar toolbarChitietsanpham;
    ImageView img_sanpham;
    TextView txtTensanpham, txtGiasanpham, txtMotasp;
    Button btnThemSanPham;
    int id = 0;
    String Tenchitiet = "";
    int Giachitiet = 0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";
    int Idsanpham = 0;
    int soluong = 1;
    int tongtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham_);
        Anhxa();
        Actiontoolbar();
        GetInfomation();
        XulyButtonThem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugiohang, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menugiohang) {
            Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void XulyButtonThem() {
        btnThemSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mangGiohang.size() > 0) {
                    boolean exits = false;
                    for (int i = 0; i < MainActivity.mangGiohang.size(); i++) {
                        if (MainActivity.mangGiohang.get(i).getIdsp() == id) {
                            MainActivity.mangGiohang.get(i).setSoluongsp(MainActivity.mangGiohang.get(i).getSoluongsp());
                            MainActivity.mangGiohang.get(i).setGiasp(Giachitiet * MainActivity.mangGiohang.get(i).getSoluongsp());
                            exits = true;
                        }
                    }
                    if (exits==false){
                        MainActivity.mangGiohang.add(new Giohang(id, Tenchitiet, Giachitiet, Hinhanhchitiet,soluong));
                    }
                } else {
                    MainActivity.mangGiohang.add(new Giohang(id, Tenchitiet, Giachitiet, Hinhanhchitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetInfomation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");

        id = sanpham.getID();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Motachitiet = sanpham.getMotasanpham();
        Hinhanhchitiet = sanpham.getHinhanhsanpham();
        Idsanpham = sanpham.getIDSanpham();
        txtTensanpham.setText(Tenchitiet);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGiasanpham.setText("Giá: " + decimalFormat.format(Giachitiet) + " Đ");

        txtMotasp.setText(Motachitiet);

        Picasso.with(getApplicationContext()).load(Hinhanhchitiet).placeholder(R.drawable.noimage)
                .error(R.drawable.error).into(img_sanpham);

    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarChitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void Anhxa() {
        toolbarChitietsanpham = (Toolbar) findViewById(R.id.toolbarChitietsanpham);
        img_sanpham = (ImageView) findViewById(R.id.img_sanpham);
        txtTensanpham = (TextView) findViewById(R.id.txtTensanpham);
        txtGiasanpham = (TextView) findViewById(R.id.txtGiasanpham);
        txtMotasp = (TextView) findViewById(R.id.txtMotachitiet);
        btnThemSanPham = (Button) findViewById(R.id.btnThemSanPham);
    }
}
