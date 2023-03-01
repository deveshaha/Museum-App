package com.example.proyectob_pmdm_t2_devesh_hanumante.apiutils;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.MuseumRes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRestService {

    public static final String BASE_URL = "https://datos.madrid.es/egob/catalogo/201132-0-museos.json/";

    @GET("201132-0-museos.json")
    Call<MuseumRes> getMuseums();

    //TODO: add the method to get the info from the api
}
