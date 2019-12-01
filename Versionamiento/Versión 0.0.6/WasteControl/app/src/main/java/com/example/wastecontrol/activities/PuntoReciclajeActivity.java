package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wastecontrol.R;
import com.example.wastecontrol.fragmen.listafragment.ListasDePuntosPorCategorias;
import com.example.wastecontrol.fragmen.listafragment.PuntosReciclajeFragment;
import com.example.wastecontrol.util.Utils;

public class PuntoReciclajeActivity extends AppCompatActivity implements PuntosReciclajeFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punto_reciclaje);

    }

    @Override
    public void onListFragmentInteraction(ListasDePuntosPorCategorias.FiltroPuntosCategoria item) {
        System.out.println(item.filtro.toString());
        Intent intent = new Intent(getBaseContext(), MapaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MapaActivity.Data.PUNTO_RECOLECCION_MODEL, item.filtro);
        bundle.putBoolean(MapaActivity.Data.ACCION_DIBUJAR_PUNTOS, Boolean.TRUE);
        intent.putExtra(MapaActivity.Data.BUNDLE, bundle);
        startActivity(intent);
    }
}
