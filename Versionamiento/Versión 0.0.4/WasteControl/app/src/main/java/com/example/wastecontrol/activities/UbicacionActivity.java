package com.example.wastecontrol.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wastecontrol.R;
import com.example.wastecontrol.fragmen.MapaFragment;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel.Key;
import com.example.wastecontrol.persistencia.respositorio.PuntoReciclajeRepository;
import com.example.wastecontrol.persistencia.respositorio.Repository;
import com.example.wastecontrol.util.ObjetoParametros;
import com.example.wastecontrol.util.Utils;

public class UbicacionActivity extends AppCompatActivity implements MapaFragment.MapaFragmentListener {

    static final int CREAR_PUNTO_RECOLECCION = 0x0001;

    MapaFragment currentFragment;
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
        Intent intent = new Intent(this, FormPuntoRecoleccionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FormPuntoRecoleccionActivity.Data.PUNTO_RECOLECCION_MODEL, Utils.transforPuntoRecoleccionModel(this.parametros));
        bundle.putBoolean(FormPuntoRecoleccionActivity.Data.ACCION_NUEVO, Boolean.TRUE);
        intent.putExtra(FormPuntoRecoleccionActivity.Data.BUNDLE, bundle);
        startActivityForResult(intent, CREAR_PUNTO_RECOLECCION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CREAR_PUNTO_RECOLECCION){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                PuntoRecoleccionModel model = (PuntoRecoleccionModel) bundle.getSerializable(FormPuntoRecoleccionActivity.Data.PUNTO_RECOLECCION_MODEL);
                Repository repository = PuntoReciclajeRepository.getInstance();
                repository.crear(model,
                        o -> {
                            UbicacionActivity.this.successDialog("Punto creado exitosamente").show();
                            System.out.println(o.toString());
                        },
                        e -> {
                            Toast.makeText(this, "error: " + e.toString(), Toast.LENGTH_LONG).show();
                        });
            }
        }
    }

    private Dialog successDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }
}
