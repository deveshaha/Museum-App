package com.example.proyectob_pmdm_t2_devesh_hanumante;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class FilterDialog extends DialogFragment {

    Spinner spnDistrict;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter, null);

        spnDistrict = view.findViewById(R.id.spnDistrict);
        ArrayAdapter<String> aDistricts = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, getDistricts());
        spnDistrict.setAdapter(aDistricts);

        AlertDialog.Builder alertD = new AlertDialog.Builder(getActivity());
        alertD.setTitle("Seleccionar Filtro");

        alertD.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO:
            }
        });

        alertD.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertD.create();
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    private ArrayList<String> getDistricts() {
        ArrayList<String> districts = new ArrayList<>();
        //districts.add("TODOS");
        districts.add("ARGANZUELA");
        districts.add("BARAJAS");
        districts.add("CARABANCHEL");
        districts.add("CENTRO");
        districts.add("CHAMARTIN");
        districts.add("CHAMBERI");
        districts.add("CIUDAD LINEAL");
        districts.add("FUENCARRAL-EL PARDO");
        districts.add("HORTALEZA");
        districts.add("LATINA");
        districts.add("MONCLOA-ARAVACA");
        districts.add("MORATALAZ");
        districts.add("PUENTE DE VALLECAS");
        districts.add("RETIRO");
        districts.add("SALAMANCA");
        districts.add("SAN BLAS-CANILLEJAS");
        districts.add("TETUAN");
        districts.add("USERA");
        districts.add("VICALVARO");
        districts.add("VILLA DE VALLECAS");
        districts.add("VILLAVERDE");

        return districts;
    }
}
