package com.ltd.tandung.amazon_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.GiohangAdapter;

import java.text.DecimalFormat;

public class GiohangActivity extends AppCompatActivity {
    ListView lvGiohang;
    public static TextView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhtoan, btnMuahang;
    Toolbar toolbar;
    GiohangAdapter giohangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        Anhxa();
        Actiontoolbar();
        GetDuLieuLV();
        Thongbao();
        XulyButton();
        XylyThanhToan();
    }

    private void XylyThanhToan() {
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void XulyButton() {
        btnMuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GiohangActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Thongbao() {
        if (MainActivity.mangGiohang.size() <= 0) {
            giohangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        } else {
            giohangAdapter.notifyDataSetChanged();
            txtThongBao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    public static void GetDuLieuLV() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.mangGiohang.size(); i++) {
            tongtien +=MainActivity.mangGiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien)+" Đ");
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvGiohang = (ListView) findViewById(R.id.lvGiohang);
        txtThongBao = (TextView) findViewById(R.id.txtThongBao);
        txtTongTien = (TextView) findViewById(R.id.txtTongTien);
        btnThanhtoan = (Button) findViewById(R.id.btnThanhtoan);
        btnMuahang = (Button) findViewById(R.id.btnMuahang);
        toolbar = (Toolbar) findViewById(R.id.toolbarGiohang);
        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.mangGiohang);
        lvGiohang.setAdapter(giohangAdapter);

    }
}
