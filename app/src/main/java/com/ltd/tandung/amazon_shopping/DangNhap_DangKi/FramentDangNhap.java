package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;
import static com.ltd.tandung.amazon_shopping.R.layout.fragment_dang_nhap;

/**
 * Created by letandung on 06/10/2017.
 */

public class FramentDangNhap extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    Button btnFacebook, btnDangNhap, btnGoogle;
    EditText edtUsername, edtMK;
    CallbackManager callbackManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(fragment_dang_nhap, container, false);
        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        btnDangNhap = (Button) view.findViewById(R.id.btnDangNhap);
        edtUsername = (EditText) view.findViewById(R.id.edtUsername);
        edtMK = (EditText) view.findViewById(R.id.edtMK);
        btnFacebook = (Button) view.findViewById(R.id.btnFacebook);
        btnGoogle = (Button) view.findViewById(R.id.btnGoogle);
        //bat su kien dang nhap facebook
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(FramentDangNhap.this, Arrays.asList("public_profile"));
            }
        });

        //bat su kien dang nhap.
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtUsername.getText().toString();
                final String passwword = edtMK.getText().toString();


                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if (success) {
                                //dang nhap thanh cong!
                                Toast.makeText(getActivity(), "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                               /* intent.putExtra("dangnhap", "dangnhap");
                                intent.putExtra("image", R.drawable.helloo);
                                intent.putExtra("text","Đăng xuất");*/

                                //luu sesson dang nhap
                                SharedPreferences preferences = getContext().getSharedPreferences("dangnhap", getContext().MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putInt("1", R.drawable.helloo);
                                editor.putString("c", username);
                                editor.putString("b", "Đăng xuất");
                                editor.commit();
                                startActivity(intent);

                                NavigationView nav = (NavigationView) view.findViewById(R.id.navigationViewManHinhChinh);
                                if (nav != null) {
                                    LinearLayout mParent = (LinearLayout) nav.getHeaderView(0);
                                    if (mParent != null) {
                                        SharedPreferences prefs = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                                        String username = prefs.getString("username", null);
                                        String password = prefs.getString("password", "User");
                                    }
                                }


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
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
