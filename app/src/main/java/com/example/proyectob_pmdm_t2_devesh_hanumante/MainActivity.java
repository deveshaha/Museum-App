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
import android.view.MenuItem;
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
    Fragment currentFragment;
    ListFragment listFragment;
    MapsFragment mapsFragment;
    String district = "";

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
                listFragment = new ListFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fl_filtro, listFragment);
                ft.commit();
                currentFragment = listFragment;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //method to check which menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: Mejorar el código
        switch (item.getItemId()) {
            case R.id.it_listado:
                listFragment = new ListFragment();
                if (currentFragment != listFragment) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_filtro, listFragment).commit();
                }
                System.out.println("FRagmento seleccionado: Listado");
                return true;
            case R.id.it_mapa:
                mapsFragment = new MapsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_filtro, mapsFragment).commit();
                currentFragment = mapsFragment;
                System.out.println("FRagmento seleccionado: Mapa");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showfilter() {
        DialogFilter filterDialog = new DialogFilter();
        filterDialog.show(getSupportFragmentManager(), "filter dialog");
        filterDialog.setCancelable(false);
    }

    @Override
    public void onAceptarDatosListener(String nombre) {
        this.district = nombre;
    }
}