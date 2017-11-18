package com.ltd.tandung.amazon_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.DangNhap_DangKi.DangNhap;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.adapter.GiohangAdapter;
import com.ltd.tandung.amazon_shopping.until.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GiohangActivity extends AppCompatActivity {
    ListView lvGiohang;
    public static TextView txtThongBao;
    static TextView txtTongTien;
    Button btnThanhtoan, btnMuahang;
    Toolbar toolbar;
    GiohangAdapter giohangAdapter;
    TextView txtUsername;

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

    //xu ly thanh toan
    private void XylyThanhToan() {
        View header = MainActivity.navigationView.getHeaderView(0);
        txtUsername = (TextView) header.findViewById(R.id.txtHienthiTen);
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tam = txtUsername.getText().toString();
                if (tam.equals("")) {
                    Toast.makeText(GiohangActivity.this, "Bạn cần đăng nhập trước khi thanh toán", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DangNhap.class);
                    startActivity(intent);
                } else {
                    if (MainActivity.mangGiohang.size() > 0) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                        final View mView = getLayoutInflater().inflate(R.layout.customdialog_thongtinkh, null);
                        Button btnXacnhan = (Button) mView.findViewById(R.id.btnXacnhan);
                        Button btnTrove = (Button) mView.findViewById(R.id.btnTrove);
                        final EditText edtName = (EditText) mView.findViewById(R.id.edtTen);
                        final EditText edtsdt = (EditText) mView.findViewById(R.id.edtSdt);
                        final EditText edtDiachi = (EditText) mView.findViewById(R.id.edtDiachi);
                        btnXacnhan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String name = edtName.getText().toString().trim();
                                final String sdt = edtsdt.getText().toString().trim();
                                final String diachi = edtDiachi.getText().toString().trim();
                                if (name.length() > 0 && sdt.length() > 0 && diachi.length() > 0) {
                                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(final String madonhang) {
                                            Log.d("madonhang", madonhang);
                                            if (Integer.parseInt(madonhang.trim()) > 0) {
                                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                                StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        if (response.equals("1")) {
                                                            MainActivity.mangGiohang.clear();
                                                            Toast.makeText(GiohangActivity.this, "Bạn đã thêm dữ liệu thành công!", Toast.LENGTH_SHORT).show();
                                                            Toast.makeText(GiohangActivity.this, "Mời bạn tiếp tục mua hàng!", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                            startActivity(intent);
                                                        } else {
                                                            Toast.makeText(GiohangActivity.this, "Có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }, new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        Toast.makeText(GiohangActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }) {
                                                    @Override
                                                    protected Map<String, String> getParams() throws AuthFailureError {
                                                        JSONArray jsonArray = new JSONArray();
                                                        for (int i = 0; i < MainActivity.mangGiohang.size(); i++) {
                                                            JSONObject jsonObject = new JSONObject();
                                                            try {
                                                                jsonObject.put("madonhang", madonhang);
                                                                jsonObject.put("masanpham", MainActivity.mangGiohang.get(i).getIdsp());
                                                                jsonObject.put("tensanpham", MainActivity.mangGiohang.get(i).getTensp());
                                                                jsonObject.put("giasanpham", MainActivity.mangGiohang.get(i).getGiasp());
                                                                jsonObject.put("soluongsanpham", MainActivity.mangGiohang.get(i).getSoluongsp());
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            jsonArray.put(jsonObject);

                                                        }
                                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                                        hashMap.put("json", jsonArray.toString());
                                                        return hashMap;
                                                    }
                                                };
                                                queue.add(request);
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            HashMap<String, String> param = new HashMap<String, String>();
                                            param.put("tenkhachhang", name);
                                            param.put("sodienthoai", sdt);
                                            param.put("diachi", diachi);
                                            return param;
                                        }
                                    };
                                    requestQueue.add(stringRequest);
                                    Intent intent = new Intent(GiohangActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(GiohangActivity.this, "Thêm đơn hàng thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(GiohangActivity.this, "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                        builder.setView(mView);
                        final AlertDialog dialog = builder.create();
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        //su kien button tro ve.
                        btnTrove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    } else
                        Toast.makeText(GiohangActivity.this, "Giỏ hàng của bạn đang trống !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //xu ly mua hang
    private void XulyButton() {
        btnMuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiohangActivity.this, MainActivity.class);
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
            tongtien += MainActivity.mangGiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien) + " Đ");
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
