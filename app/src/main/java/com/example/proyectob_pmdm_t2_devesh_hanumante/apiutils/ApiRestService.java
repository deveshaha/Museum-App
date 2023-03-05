package com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Graph;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRestService {

    public static final String BASE_URL = "https://datos.madrid.es/egob/catalogo/";

    @GET("201132-0-museos.json")
    Call<Graph> getMuseums(@Query("distrito_nombre") String district);

    @GET("tipo/entidadesyorganismos/{id}")
    Call<Graph> getMusuemById(@Path("id") String id);
}
