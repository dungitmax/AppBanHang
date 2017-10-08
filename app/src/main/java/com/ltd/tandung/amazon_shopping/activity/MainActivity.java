package com.ltd.tandung.amazon_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.DangNhap_DangKi.DangNhap;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.LoaispAdapter;
import com.ltd.tandung.amazon_shopping.adapter.SanphamAdapter;
import com.ltd.tandung.amazon_shopping.model.Giohang;
import com.ltd.tandung.amazon_shopping.model.Loaisp;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.ltd.tandung.amazon_shopping.until.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getSimpleName();
    Toolbar toolbarManHinhChinh;
    ViewFlipper viewFlipperManHinhChinh;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationViewManHinhChinh;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayoutManHinhChinh;
    ActionBarDrawerToggle mToggle;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    ArrayList<Sanpham> listSanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> mangGiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ActionBar();
        ActionViewFlipper();
        GetdulieusanphamNEW();
        SetOnheader();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void SetOnheader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        View hView = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) hView.findViewById(R.id.image_Login);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DangNhap.class);
                startActivity(intent);
            }
        });
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

    private void GetdulieusanphamNEW() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String Tensanpham = "";
                    Integer Giasanpham = 0;
                    String Hinhanhsanpham = "";
                    String Motasanpham = "";
                    int IDsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            ID = object.getInt("Id");
                            Tensanpham = object.getString("Tensanpham");
                            Giasanpham = object.getInt("Giasanpham");
                            Hinhanhsanpham = object.getString("Hinhanhsanpham");
                            Motasanpham = object.getString("Motasanpham");
                            IDsanpham = object.getInt("Idsanpham");

                            listSanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrQuangCao = new ArrayList<>();
        arrQuangCao.add("http://imageshack.com/a/img922/9155/iOkjSP.jpg");
        arrQuangCao.add("http://imageshack.com/a/img924/9688/wDnccH.jpg");
        arrQuangCao.add("http://imageshack.com/a/img924/3192/xYhjFh.jpg");
        arrQuangCao.add("http://imageshack.com/a/img923/7789/Uwkrrk.png");
        arrQuangCao.add("https://topy.info/wp-content/uploads/2016/07/Nh%E1%BB%AFng-cu%E1%BB%91n-s%C3%A1ch-hay-nh%E1%BA%A5t-m%E1%BB%8Di-th%E1%BB%9Di-%C4%91%E1%BA%A1i.jpeg");
        for (int i = 0; i < arrQuangCao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(arrQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperManHinhChinh.addView(imageView);

        }
        viewFlipperManHinhChinh.setFlipInterval(5000);
        viewFlipperManHinhChinh.setAutoStart(true);
        Animation animationSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animationSlideOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipperManHinhChinh.setInAnimation(animationSlideIn);
        viewFlipperManHinhChinh.setOutAnimation(animationSlideOut);
    }

    private void ActionBar() {
        setSupportActionBar(toolbarManHinhChinh);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarManHinhChinh.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbarManHinhChinh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutManHinhChinh.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {

        toolbarManHinhChinh = (Toolbar) findViewById(R.id.toolbarManHinhChinh);
        viewFlipperManHinhChinh = (ViewFlipper) findViewById(R.id.viewFlipperManHinhChinh);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyclerViewManHinhChinh);
        navigationViewManHinhChinh = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        //listViewManHinhChinh = (ListView) findViewById(R.id.listViewManHinhChinh);
        drawerLayoutManHinhChinh = (DrawerLayout) findViewById(R.id.drawerLayoutManHinhChinh);
//        mangloaisp = new ArrayList<>();
//        loaispAdapter = new LoaispAdapter(mangloaisp, getApplicationContext());
//        listViewManHinhChinh.setAdapter(loaispAdapter);

        listSanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), listSanpham);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        recyclerViewManHinhChinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewManHinhChinh.setAdapter(sanphamAdapter);

        if (mangGiohang != null) {

        } else {
            mangGiohang = new ArrayList<>();

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.trangchu) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_tttk) {
            Toast.makeText(this, "thong tin tk", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_dmk) {
            Toast.makeText(this, "doi mk", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_bhth) {
            Intent intent = new Intent(MainActivity.this, BHTTActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_ldsk) {
            Intent intent = new Intent(MainActivity.this, LD_SKActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_mvb) {
            Intent intent = new Intent(MainActivity.this, Me_BeActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_ncds) {
            Intent intent = new Intent(MainActivity.this, Nha_DSActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_s) {
            Intent intent = new Intent(MainActivity.this, SachActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_tt) {
            Intent intent = new Intent(MainActivity.this, ThoitrangActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_ddt) {
            Intent intent = new Intent(MainActivity.this, DoDTActivity.class);
            startActivity(intent);
        } else if (id == R.id.tv_ttlh) {
           Intent intent = new Intent(MainActivity.this, Thongtinlienhe.class);
          startActivity(intent);
       }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayoutManHinhChinh);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
