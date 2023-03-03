package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MuseumDetailed extends AppCompatActivity {

    TextView tvMuseumName, tvMuseumAddress, tvMuseumInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detailed);

        tvMuseumName = findViewById(R.id.tv_museum_name);
        tvMuseumAddress = findViewById(R.id.tv_museum_address);
        tvMuseumInfo = findViewById(R.id.tv_museum_info);

        tvMuseumName.setText(getIntent().getStringExtra("name"));
        tvMuseumAddress.setText(getIntent().getStringExtra("address"));
        tvMuseumInfo.setText(getIntent().getStringExtra("info"));

    }
}