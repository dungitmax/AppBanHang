package com.ltd.tandung.amazon_shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ltd.tandung.amazon_shopping.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhchao);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {

                } finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this, MainActivity.class);
                    startActivity(intent);
                    ManHinhChaoActivity.this.finish();
                }
            }
        });
        thread.start();

    }
}
