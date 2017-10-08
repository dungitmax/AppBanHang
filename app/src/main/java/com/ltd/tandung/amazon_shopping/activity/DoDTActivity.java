package com.ltd.tandung.amazon_shopping.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.DoDTAdapter;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.ltd.tandung.amazon_shopping.until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.ltd.tandung.amazon_shopping.R.id.lvID;
import static com.ltd.tandung.amazon_shopping.R.id.toolbarID;

public class DoDTActivity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lv;
    ArrayList<Sanpham> mangsp;
    DoDTAdapter adapter;
    String urlGetdata = Server.Duongdansanphamelectronic;

    View footerview;
    boolean isLoading = false;
    boolean limitdata = false;
    mHandler mHandler;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_dt);
        Anhxa();
        Actiontoolbar();
        GetData(urlGetdata, page);
        LoadMoreData();

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

    private void LoadMoreData() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChitietsanphamActivity.class);
                intent.putExtra("thongtinsanpham", mangsp.get(position));
                startActivity(intent);
            }
        });
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (view.getLastVisiblePosition() == totalItemCount - 1 && totalItemCount != 0 && isLoading == false && limitdata == false) {
                    isLoading = true;
                    ThredData thredData = new ThredData();
                    thredData.start();
                }
            }
        });
    }

    private void GetData(String url, int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null && response.length() != 2) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            int id = object.getInt("Id");
                            String tensp = object.getString("Tensanpham");
                            int giasp = object.getInt("Giasanpham");
                            String hinhanhsp = object.getString("Hinhanhsanpham");
                            String motasp = object.getString("Motasanpham");
                            int idloaisp = object.getInt("Idsanpham");
                            mangsp.add(new Sanpham(id, tensp, giasp, hinhanhsp, motasp, idloaisp));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    limitdata = true;
                    lv.removeFooterView(footerview);
                    Toast.makeText(DoDTActivity.this, "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
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
        toolbar = (Toolbar) findViewById(toolbarID);
        lv = (ListView) findViewById(lvID);
        mangsp = new ArrayList<>();
        adapter = new DoDTAdapter(this, R.layout.custom_dong_sanpham, mangsp);
        lv.setAdapter(adapter);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.loadmore, null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lv.addFooterView(footerview);
                    break;
                case 1:
                    GetData(urlGetdata, ++page);
                    isLoading = false;
                    break;
            }
        }
    }

    public class ThredData extends Thread {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
