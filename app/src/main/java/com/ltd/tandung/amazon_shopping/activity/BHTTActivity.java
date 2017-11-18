package com.ltd.tandung.amazon_shopping.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.BHTHAdapter;
import com.ltd.tandung.amazon_shopping.model.Sanpham;
import com.ltd.tandung.amazon_shopping.until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BHTTActivity extends AppCompatActivity {
    Toolbar toolbarBHTT;
    ListView lvBHTT;
    ArrayList<Sanpham> mangdhth;
    BHTHAdapter bhthAdapter;
    String urlGetdata = Server.Duongdansanphambhth;

    View footerview;
    boolean isLoading = false;
    boolean limitdata = false;
    mHandler mHandler;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bhtt);
        Anhxa();
        Actiontoolbar();
        GetData(urlGetdata, page);
        LoadMoreData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugiohang, menu);
        MenuItem searchItem = menu.findItem(R.id.search_view);
        SearchManager searchManager = (SearchManager) BHTTActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchManager != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(BHTTActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    bhthAdapter.Filter(newText);
                    lvBHTT.invalidate();
                    return true;
                }
            });

        }
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
        lvBHTT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChitietsanphamActivity.class);
                intent.putExtra("thongtinsanpham", mangdhth.get(position));
                startActivity(intent);
            }
        });
        lvBHTT.setOnScrollListener(new AbsListView.OnScrollListener() {
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

    private void GetData(String url, final int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null && response.length() != 2) {
                    lvBHTT.removeFooterView(footerview);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            int id = object.getInt("Id");
                            String tensp = object.getString("Tensanpham");
                            int giasp = object.getInt("Giasanpham");
                            String hinhanhsp = object.getString("Hinhanhsanpham");
                            String motasp = object.getString("Motasanpham");
                            int idloaisp = object.getInt("Idsanpham");
                            mangdhth.add(new Sanpham(id, tensp, giasp, hinhanhsp, motasp, idloaisp));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    bhthAdapter = new BHTHAdapter(getApplicationContext(), R.layout.custom_dong_sanpham, mangdhth);
                    lvBHTT.setAdapter(bhthAdapter);
                    bhthAdapter.notifyDataSetChanged();
                } else {
                    limitdata = true;
                    lvBHTT.removeFooterView(footerview);
                    Toast.makeText(BHTTActivity.this, "Đã hết dữ liệu", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("limit", page + "");
                return stringMap;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    private void Actiontoolbar() {
        setSupportActionBar(toolbarBHTT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarBHTT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void Anhxa() {
        toolbarBHTT = (Toolbar) findViewById(R.id.toolbarBHTT);
        lvBHTT = (ListView) findViewById(R.id.lvBHTT);
        mangdhth = new ArrayList<>();

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.loadmore, null);
        mHandler = new mHandler();

    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    lvBHTT.addFooterView(footerview);
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
