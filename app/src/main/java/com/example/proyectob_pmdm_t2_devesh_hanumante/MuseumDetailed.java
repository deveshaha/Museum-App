package com.example.proyectob_pmdm_t2_devesh_hanumante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Graph;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.ApiRestService;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MuseumDetailed extends AppCompatActivity {

    TextView tvMuseumName, tvMuseumAddress, tvMuseumInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detailed);

        tvMuseumName = findViewById(R.id.tv_museum_name);
        tvMuseumAddress = findViewById(R.id.tv_museum_address);
        tvMuseumInfo = findViewById(R.id.tv_museum_info);

        Intent intent = getIntent();
        String id = "";

        if (intent != null) {
            id = intent.getStringExtra("id");
            System.out.println("id recibido del intent: " + id);
        }

        Retrofit retrofit = RetrofitClient.getClient(ApiRestService.BASE_URL);
        ApiRestService apiRestService = retrofit.create(ApiRestService.class);
        Call<Graph> call = apiRestService.getMuseums(id);

        call.enqueue(new Callback<Graph>() {
            @Override
            public void onResponse(Call<Graph> call, @NonNull Response<Graph> response) {
                if (response.isSuccessful()) {
                    Graph graph = response.body();
                    String name = graph.getMuseum().get(0).getTitle();
                    String address = String.valueOf(graph.getMuseum().get(0).getAddress());
                    String info = String.valueOf(graph.getMuseum().get(0).getOrganization());

                    System.out.println("name: " + name);
                    System.out.println("address: " + address);
                    System.out.println("info: " + info);

                    tvMuseumName.setText(name);
                    tvMuseumAddress.setText(address);
                    tvMuseumInfo.setText(info);
                }
            }

            @Override
            public void onFailure(Call<Graph> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), "Error al obtener los datos", Snackbar.LENGTH_LONG).show();
            }
        });

    }
}