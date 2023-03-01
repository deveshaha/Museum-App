package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.MuseumRes;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.ApiRestService;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.RetrofitClient;
import com.example.proyectob_pmdm_t2_devesh_hanumante.dialog.DialogFilter;
import com.example.proyectob_pmdm_t2_devesh_hanumante.rvutils.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements DialogFilter.OnDatosListener {

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
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = new ListFragment();
                ft.add(R.id.fl_filtro, fragment);
                ft.commit();
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

    @Override
    public void onAceptarDatosListener(String nombre) {
        Log.d("TAG", "onAceptarDatosListener: " + nombre);
    }
}