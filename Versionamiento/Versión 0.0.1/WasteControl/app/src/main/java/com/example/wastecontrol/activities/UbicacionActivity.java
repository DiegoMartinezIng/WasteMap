package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wastecontrol.R;
import com.example.wastecontrol.fragmen.MapaFragment;
import com.example.wastecontrol.util.ObjetoParametros;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel.Key;
import com.example.wastecontrol.util.Utils;

public class UbicacionActivity extends AppCompatActivity implements MapaFragment.MapaFragmentListener {

    Fragment currentFragment;
    private ObjetoParametros parametros;
    private TextView txtDireccion;
    private TextView txtCiudad;
    private TextView txtDepartamento;
    private TextView txtPais;
    private TextView txtUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        this.txtDireccion = (TextView) findViewById(R.id.txtDireccion);
        this.txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        this.txtDepartamento = (TextView) findViewById(R.id.txtDepartamento);
        this.txtPais = (TextView) findViewById(R.id.txtPais);
        this.txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
        currentFragment = MapaFragment.newInstance(null, null);
        changeFragment(currentFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit();
    }


    @Override
    public void updateUbicacion(ObjetoParametros parametros) {
        this.parametros = parametros;
        cargarDatos(this.parametros);
    }

    private void cargarDatos(ObjetoParametros ubicacion) {
        txtDireccion.setText(String.format("DIRECCION: %s", ubicacion.get(Key.DIRECCION, String.class)));
        txtCiudad.setText(String.format("CIUDAD: %s", ubicacion.get(Key.CIUDAD, String.class)));
        txtDepartamento.setText(String.format("DEPARTAMENTO: %s", ubicacion.get(Key.DEPARTAMENTO, String.class)));
        txtPais.setText(String.format("PAIS: %s", ubicacion.get(Key.PAIS, String.class)));
        txtUbicacion.setText(String.format("UBICACION: %s", ubicacion.get(Key.UBICACION, String.class)));
    }

    public void nuevoPuntoReciclaje(View view){
        Intent intent = new Intent(this, FormPuntoRecoleccion.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FormPuntoRecoleccion.Data.PUNTO_RECOLECCION_MODEL, Utils.transforPuntoRecoleccionModel(this.parametros));
        bundle.putBoolean(FormPuntoRecoleccion.Data.ACCION_NUEVO, Boolean.TRUE);
        intent.putExtra(FormPuntoRecoleccion.Data.BUNDLE, bundle);
        startActivity(intent);
    }
}
