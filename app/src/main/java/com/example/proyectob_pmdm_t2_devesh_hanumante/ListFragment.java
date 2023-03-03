package com.example.proyectob_pmdm_t2_devesh_hanumante;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.MuseumRes;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.ApiRestService;
import com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils.RetrofitClient;
import com.example.proyectob_pmdm_t2_devesh_hanumante.rvutils.ListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListFragment extends Fragment {
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private ListAdapter listAdapter;
    private List<Museum> museums = new ArrayList<>();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listAdapter = new ListAdapter(museums);
        listAdapter.setOnClickListeneer(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = rv.getChildAdapterPosition(v);

                Snackbar.make(v, "Museum: " + museums.get(position).getTitle(), Snackbar.LENGTH_LONG).show();

                String museumName = museums.get(position).getTitle();
                String museumAddress = String.valueOf(museums.get(position).getAddress());
                String museumDescription = museums.get(position).getRelation();

                Intent intent = new Intent(getActivity(), MuseumDetailed.class);
                intent.putExtra("museumName", museumName);
                intent.putExtra("museumAddress", museumAddress);
                intent.putExtra("museumDescription", museumDescription);
                startActivity(intent);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rv = view.findViewById(R.id.rv_museums);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        getMuseumsInfo();
        return view;
    }

    private void getMuseumsInfo() {
        Retrofit retrofit = RetrofitClient.getClient(ApiRestService.BASE_URL);
        ApiRestService apiRestService = retrofit.create(ApiRestService.class);
        Call<MuseumRes> call = apiRestService.getMuseums();

        call.enqueue(new Callback<MuseumRes>() {
            @Override
            public void onResponse(Call<MuseumRes> call, Response<MuseumRes> response) {
                if (response.isSuccessful()) {
                    MuseumRes museumRes = response.body();
                    if (museumRes != null) {
                        museums = museumRes.getMuseum();
                    } else {
                        Log.e("Error", "Error en la respuesta");
                    }
                    listAdapter = new ListAdapter(museums);
                    rv.setAdapter(listAdapter);
                } else {
                    Log.e("Error", "Error en la respuesta");
                }
            }

            @Override
            public void onFailure(Call<MuseumRes> call, Throwable t) {
                Log.e("Error", "Error en la respuesta");
            }
        });

    }
}