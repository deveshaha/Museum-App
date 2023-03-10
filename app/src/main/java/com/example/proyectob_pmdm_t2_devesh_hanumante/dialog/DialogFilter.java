package com.example.proyectob_pmdm_t2_devesh_hanumante.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectob_pmdm_t2_devesh_hanumante.R;

public class DialogFilter extends DialogFragment {

    Spinner spnDistrict;
    OnDatosListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_filter, null);

        spnDistrict = view.findViewById(R.id.spnDistrict);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.districts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDistrict.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        builder.setTitle("Filtrar por Distrito").setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onAceptarDatosListener(spnDistrict.getSelectedItem().toString());
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        AlertDialog dialog = builder.create();
        return dialog;

    }

    public interface OnDatosListener{
        void onAceptarDatosListener(String nombre);
    }

    @Override
    public void onAttach(@NonNull Activity Activity) {
        super.onAttach(Activity);
        try {
            listener = (OnDatosListener) Activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(Activity.toString() + " must implement OnDatosListener");
        }
    }

    @Override
    public void onDetach() {
        if (listener != null){
            listener = null;
        }
        super.onDetach();
    }
}
