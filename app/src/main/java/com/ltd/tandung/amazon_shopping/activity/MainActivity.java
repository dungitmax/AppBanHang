package com.ltd.tandung.amazon_shopping.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.DangNhap_DangKi.DangNhap;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.LoaispAdapter;
import com.ltd.tandung.amazon_shopping.adapter.SanphamAdapter;
import com.ltd.tandung.amazon_shopping.adapter.ViewPagerAdapter;
import com.ltd.tandung.amazon_shopping.model.Giohang;
import com.ltd.tandung.amazon_shopping.model.Loaisp;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.ltd.tandung.amazon_shopping.until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = getClass().getSimpleName();
    private SearchView mSearchView;
    Toolbar toolbarManHinhChinh;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationViewManHinhChinh;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayoutManHinhChinh;
    ActionBarDrawerToggle mToggle;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    ArrayList<Sanpham> listSanpham;
    SanphamAdapter sanphamAdapter;
    ViewPager viewPager;
    LinearLayout sliderDots;
    int dotscount;
    ImageView[] dots;
    public static NavigationView navigationView;

    public static ArrayList<Giohang> mangGiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        View hView = navigationView.getHeaderView(0);
        final ImageView imageView = (ImageView) hView.findViewById(R.id.image_Login);
        final TextView textView = (TextView) hView.findViewById(R.id.txtDangNhap);
        final TextView textView1 = (TextView) hView.findViewById(R.id.txtHienthiTen);
        /*Intent intent = getIntent();
        if (intent.getIntExtra("image", 1) != 1) {
            imageView.setImageResource(intent.getIntExtra("image", 1));
        }
        if (intent.getStringExtra("text") != null) {
            textView.setText(intent.getStringExtra("text"));
        }*/
        SharedPreferences preferences = getSharedPreferences("dangnhap", MODE_PRIVATE);
        imageView.setImageResource(preferences.getInt("1", R.drawable.bt_dangnhap));
        textView.setText(preferences.getString("b", "Đăng nhập"));
        textView1.setText(preferences.getString("c", ""));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                SharedPreferences settings = getSharedPreferences("dangnhap", MODE_PRIVATE);
                settings.edit().clear().commit();
                startActivity(intent);
            }
        });


        Anhxa();
        ActionBar();
        //ActionViewFlipper();
        GetdulieusanphamNEW();
        SetOnheader();
        ActionViewPagerIndicator();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void ActionViewPagerIndicator() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(4);
                    } else if (viewPager.getCurrentItem() == 4) {
                        viewPager.setCurrentItem(5);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }

    private void SetOnheader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        View hView = navigationView.getHeaderView(0);
        final ImageView imageView = (ImageView) hView.findViewById(R.id.image_Login);
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
        // viewFlipperManHinhChinh = (ViewFlipper) findViewById(R.id.viewFlipperManHinhChinh);
        recyclerViewManHinhChinh = (RecyclerView) findViewById(R.id.recyclerViewManHinhChinh);
        navigationViewManHinhChinh = (NavigationView) findViewById(R.id.navigationViewManHinhChinh);
        drawerLayoutManHinhChinh = (DrawerLayout) findViewById(R.id.drawerLayoutManHinhChinh);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDots = (LinearLayout) findViewById(R.id.SliderDots);
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
            Intent intent = new Intent(MainActivity.this, Thongtintaikhoan.class);
            startActivity(intent);
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("THOÁT")
                .setMessage("Bạn có chắc chắn thoát khỏi ứng dụng?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
//                        homeIntent.addCategory( Intent.CATEGORY_HOME );
//                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(homeIntent);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}
