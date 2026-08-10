package com.min.applocker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LockActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout dibikin pake kode biar 1 file aja
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,200,50,0);

        EditText etPin = new EditText(this);
        etPin.setHint("Masukkan PIN");
        etPin.setInputType(129); // numberPassword

        Button btn = new Button(this);
        btn.setText("Buka");

        layout.addView(etPin);
        layout.addView(btn);
        setContentView(layout);

        String target = getIntent().getStringExtra("target");
        SharedPreferences sp = getSharedPreferences("AppLock", MODE_PRIVATE);
        if(!sp.contains("PIN")) sp.edit().putString("PIN", "1234").apply();

        btn.setOnClickListener(v -> {
            if(etPin.getText().toString().equals(sp.getString("PIN", ""))){
                startActivity(getPackageManager().getLaunchIntentForPackage(target));
                finish();
            } else Toast.makeText(this, "PIN Salah!", Toast.LENGTH_SHORT).show();
        });
    }
    @Override public void onBackPressed() { moveTaskToBack(true); }
}