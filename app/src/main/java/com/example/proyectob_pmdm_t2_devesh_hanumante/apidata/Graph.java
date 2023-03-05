
package com.example.proyectob_pmdm_t2_devesh_hanumante.apidata;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Graph {

    @SerializedName("@context")
    @Expose
    private Context context;
    @SerializedName("@graph")
    @Expose
    private List<Museum> museum;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Museum> getMuseum() {
        return museum;
    }

    public void setMuseum(List<Museum> museum) {
        this.museum = museum;
    }

}
