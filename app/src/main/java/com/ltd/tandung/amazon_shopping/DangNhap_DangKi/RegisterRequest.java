package com.ltd.tandung.amazon_shopping.DangNhap_DangKi;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.ltd.tandung.amazon_shopping.until.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by letandung on 07/10/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String url = Server.DuongdanRegister;
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, String sex, Response.Listener<String> listener) {
        super(Method.POST, url, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("sex", sex);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
