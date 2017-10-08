package com.ltd.tandung.amazon_shopping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ltd.tandung.amazon_shopping.DangNhap_DangKi.FramentDangKi;
import com.ltd.tandung.amazon_shopping.DangNhap_DangKi.FramentDangNhap;

/**
 * Created by letandung on 07/10/2017.
 */

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FramentDangNhap framentDangNhap = new FramentDangNhap();
                return framentDangNhap;
            case 1:
                FramentDangKi framentDangKi = new FramentDangKi();
                return framentDangKi;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ĐĂNG NHẬP";
            case 1:
                return "ĐĂNG KÍ";
            default:
                return null;
        }
    }
}
