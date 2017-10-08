package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.ViewPagerAdapterDangNhap;

public class DangNhap extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabs;
    ViewPager container;
    ViewPagerAdapterDangNhap viewPagerAdapterDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        tabs = (TabLayout) findViewById(R.id.tabs);
        container = (ViewPager) findViewById(R.id.container);
        viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        container.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();
        tabs.setupWithViewPager(container);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
