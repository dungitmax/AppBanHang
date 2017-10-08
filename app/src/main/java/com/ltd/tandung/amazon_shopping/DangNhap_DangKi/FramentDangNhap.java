package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ltd.tandung.amazon_shopping.R.layout.fragment_dang_nhap;

/**
 * Created by letandung on 06/10/2017.
 */

public class FramentDangNhap extends Fragment {
    Button btnFacebook, btnDangNhap, btnGoogle;
    EditText edtUsername, edtMK;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(fragment_dang_nhap, container, false);
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edtUsername = (EditText) view.findViewById(R.id.edtUsername);
        edtMK = (EditText) view.findViewById(R.id.edtMK);
        btnFacebook = (Button) view.findViewById(R.id.btnFacebook);
        btnGoogle = (Button) view.findViewById(R.id.btnGoogle);
        //bat su kien dang nhap.
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String passwword = edtMK.getText().toString();
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if (success) {
                                SharedPreferences preferences = getContext().getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                String tendn = object.getString("username");
                                editor.putString("username",tendn);
                                editor.commit();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getActivity(), "Tài khoản hoặc Mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest request = new LoginRequest(username, passwword, listener);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(request);
                ////////////////////////////////////////

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
