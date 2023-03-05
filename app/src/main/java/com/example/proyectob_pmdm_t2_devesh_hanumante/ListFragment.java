package com.example.proyectob_pmdm_t2_devesh_hanumante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectob_pmdm_t2_devesh_hanumante.apidata.Museum;
import com.example.proyectob_pmdm_t2_devesh_hanumante.rvutils.ListAdapter;

import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView rv;
    private LinearLayoutManager llm;
    ListAdapter adapter;

    public ListFragment() {
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rv = view.findViewById(R.id.rv_museums);
        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        return view;
    }

    public void passMuseumInfo(List<Museum> museumList) {
        if (rv != null) { //checamos que el recycler view no sea nulo para evitar errores
            ListAdapter listAdapter = new ListAdapter(museumList);
            rv.setAdapter(listAdapter);
        }
    }
}