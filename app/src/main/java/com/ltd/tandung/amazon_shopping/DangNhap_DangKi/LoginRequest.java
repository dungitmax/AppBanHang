package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.ltd.tandung.amazon_shopping.until.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by letandung on 07/10/2017.
 */

public class LoginRequest extends StringRequest {
    private final static String url = Server.DuongdanLogin;
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
