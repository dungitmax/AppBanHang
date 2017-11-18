package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.ltd.tandung.amazon_shopping.R;
import com.ltd.tandung.amazon_shopping.activity.DieukhoansudungActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by letandung on 06/10/2017.
 */

public class FramentDangKi extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, ViewDangKi {
    RadioGroup rg;
    EditText edtHoten, edtUsername, edtPassword, edtSex;
    RadioButton radNam, radNu;
    Button btnDangKi;
    TextInputLayout input_editHoten;
    TextInputLayout input_editUsername;
    TextInputLayout input_Password;
    TextView txtDieuKhoan;
    boolean kiemtra = false;
    String textGT = "";

    public void getJSon() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangki, container, false);
        edtHoten = (EditText) view.findViewById(R.id.edtHoten);
        edtUsername = (EditText) view.findViewById(R.id.edtUsername);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        txtDieuKhoan = (TextView) view.findViewById(R.id.txtDieuKhoan);
        input_editHoten = (TextInputLayout) view.findViewById(R.id.input_edtHoten);
        input_editUsername = (TextInputLayout) view.findViewById(R.id.input_edtEmail);
        input_Password = (TextInputLayout) view.findViewById(R.id.input_edtMK);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        radNam = (RadioButton) view.findViewById(R.id.radNam);
        radNu = (RadioButton) view.findViewById(R.id.radNu);
        btnDangKi = (Button) view.findViewById(R.id.btnDangKi);
        edtHoten.setOnFocusChangeListener(this);
        edtUsername.setOnFocusChangeListener(this);
        //bat su kien check gt
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radNam) {
                    Integer id = radNam.getId();
                    textGT = id.toString();

                } else if (checkedId == R.id.radNu) {
                    Integer id = radNu.getId();
                    textGT = id.toString();
                }
            }
        });
        //bat su kien dieu khoan su dung.
        txtDieuKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), DieukhoansudungActivity.class);
                startActivity(intent);
            }
        });


        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtHoten.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            boolean success = object.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(getActivity(), DangNhap.class);
                                Toast.makeText(getActivity(), "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                Toast.makeText(getActivity(), "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest request = new RegisterRequest(name, username, password, textGT, listener);
                RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
                queue.add(request);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnDangKi) {
            btnDangKi();
            return;
        }
    }

    private void btnDangKi() {
        String name = edtHoten.getText().toString();
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id) {
            case R.id.edtHoten:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_editHoten.setErrorEnabled(false);
                        input_editHoten.setError("Bạn chưa nhâp họ tên!");
                        kiemtra = false;

                    } else {
                        input_editHoten.setErrorEnabled(false);
                        input_editHoten.setError("");
                        kiemtra = true;
                    }
                }
                break;
            case R.id.edtUsername:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_editUsername.setErrorEnabled(true);
                        input_editUsername.setError("Bạn chưa nhâp tài khoản !");
                        kiemtra = false;

//                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(chuoi).matches()) {
//                        input_editUsername.setErrorEnabled(true);
//                        input_editUsername.setError(" Username không hợp lệ !");
//                        kiemtra = false;
                    } else {
                        input_editUsername.setErrorEnabled(false);
                        input_editUsername.setError("");
                        kiemtra = true;
                    }
                }
                break;
            case R.id.input_edtMK:
                if (!hasFocus) {
                    String chuoi = ((EditText) v).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        input_Password.setErrorEnabled(true);
                        input_Password.setError("Bạn chưa nhâp mật khẩu !");
                        kiemtra = false;

                    } else {
                        input_Password.setErrorEnabled(false);
                        input_Password.setError("");
                        kiemtra = true;
                    }
                }
                break;
        }
    }

    @Override
    public void DangKyThangCong() {
        Toast.makeText(getActivity(), "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
    }
}
