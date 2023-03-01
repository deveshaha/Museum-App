package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.proyectob_pmdm_t2_devesh_hanumante.dialog.DialogFilter;

public class MainActivity extends AppCompatActivity {

    Button btnFilter, btnConsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilter = findViewById(R.id.btn_filtrar);
        btnConsult = findViewById(R.id.btn_consultar);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilter();
            }
        });

        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: get info from api
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void showfilter() {
        DialogFilter filterDialog = new DialogFilter();
        filterDialog.show(getSupportFragmentManager(), "filter dialog");
        filterDialog.setCancelable(false);
    }
}