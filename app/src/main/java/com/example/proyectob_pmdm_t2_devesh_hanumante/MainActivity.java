package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Graph;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.ApiRestService;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.RetrofitClient;
import com.example.proyectob_pmdm_t2_devesh_hanumante.dialog.DialogFilter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements DialogFilter.OnDatosListener {

    Button btnFilter, btnConsult;
    Fragment fragment;
    ListFragment listFragment;
    MapsFragment mapsFragment;
    String district = "";
    TextView tvSelectedFilter;

    List<Museum> museumList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFilter = findViewById(R.id.btn_filtrar);
        btnConsult = findViewById(R.id.btn_consultar);
        tvSelectedFilter = findViewById(R.id.tv_filtro_sel);
        tvSelectedFilter.setVisibility(View.INVISIBLE);

        listFragment = new ListFragment();
        mapsFragment = new MapsFragment();

        //cargamos el fragmento de la lista por default
        getSupportFragmentManager().beginTransaction().add(R.id.fl_filtro, listFragment).commit();
        fragment = listFragment; //asignamos el fragmento de la lista a la variable fragment para saber que fragmento está activo
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().remove(listFragment).commit();
                showfilter();
            }
        });

        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSelectedFilter.setVisibility(View.INVISIBLE);
                getMuseumsInfo();
            }
        });
    }

    private void getMuseumsInfo() {
        //inicializamos el cliente retrofit
        Retrofit retrofit = RetrofitClient.getClient(ApiRestService.BASE_URL);
        ApiRestService apiRestService = retrofit.create(ApiRestService.class);
        Call<Graph> call = apiRestService.getMuseums(district);

        call.enqueue(new Callback<Graph>() {
            @Override
            public void onResponse(Call<Graph> call, Response<Graph> response) {
                if (response.isSuccessful()) {
                    Graph graph = response.body();
                    List<Museum> museumList = graph.getMuseum();
                    if (fragment == listFragment) {
                        listFragment.passMuseumInfo(museumList); //pasamos la lista de museos al fragmento de la lista
                        tvSelectedFilter.setVisibility(View.VISIBLE);

                        if (district.isEmpty()) {
                            tvSelectedFilter.setText(R.string.all_museums);
                        } else {
                            String selectedFilterText = getString(R.string.selected_filter, district);
                            tvSelectedFilter.setText(selectedFilterText);
                        }

                        if (museumList.isEmpty()) {
                            Snackbar.make(getWindow().getDecorView().getRootView(), "No hay museos en ese distrito", Snackbar.LENGTH_LONG).show();
                        }
                    } else {
                        mapsFragment.passMuseumInfo(museumList); //pasamos la lista de museos al fragmento del mapa
                        tvSelectedFilter.setVisibility(View.VISIBLE);

                        if (district.isEmpty()) {
                            tvSelectedFilter.setText(R.string.all_museums);
                        } else {
                            String selectedFilterText = getString(R.string.selected_filter, district);
                            tvSelectedFilter.setText(selectedFilterText);
                        }

                        if (museumList.isEmpty()) {
                            Snackbar.make(getWindow().getDecorView().getRootView(), "No hay museos en ese distrito", Snackbar.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Snackbar.make(getWindow().getDecorView().getRootView(), "Error en la respuesta", Snackbar.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Graph> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().getRootView(), "Error en la conexión", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.it_listado) {
            if (fragment != listFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_filtro, listFragment).commit();
                district = "";
                fragment = listFragment;
                tvSelectedFilter.setVisibility(View.INVISIBLE);
                btnConsult.setText(R.string.btn_consultar);
            }
            return true;
        } else if (item.getItemId() == R.id.it_mapa) {
            if (fragment != mapsFragment) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_filtro, mapsFragment).commit();
                district = "";
                fragment = mapsFragment;
                tvSelectedFilter.setVisibility(View.INVISIBLE);
                btnConsult.setText(R.string.btn_consultar_mapa);
            }
            return true;
        } else {
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