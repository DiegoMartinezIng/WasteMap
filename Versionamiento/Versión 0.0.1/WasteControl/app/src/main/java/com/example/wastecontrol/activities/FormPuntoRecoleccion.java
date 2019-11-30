package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wastecontrol.R;
import com.example.wastecontrol.R2;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPuntoRecoleccion extends AppCompatActivity {

    private PuntoRecoleccionModel puntoRecoleccionModel;

    @BindView(R.id.txtNombrePuntoRecoleccion)
    EditText txtNombrePuntoRecoleccion;

    @BindView(R.id.txtDireccion)
    EditText txtDireccion;

    @BindView(R.id.txtCiudad)
    EditText txtCiudad;

    @BindView(R.id.txtDepartamento)
    EditText txtDepartamento;

    @BindView(R.id.txtPais)
    EditText txtPais;

    @BindView(R.id.txtUbicacion)
    EditText txtUbicacion;

    @BindView(R.id.btnAceptar)
    Button btnAceptar;

    @BindView(R.id.btnCancelar)
    Button btnCancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_punto_reciclaje);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getBundleExtra(Data.BUNDLE);
        puntoRecoleccionModel = (PuntoRecoleccionModel) bundleExtra.getSerializable(Data.PUNTO_RECOLECCION_MODEL);
        txtDireccion.setText(puntoRecoleccionModel.getDireccion());
        txtCiudad.setText(puntoRecoleccionModel.getCiudad());
        txtDepartamento.setText(puntoRecoleccionModel.getDepartamento());
        txtPais.setText(puntoRecoleccionModel.getPais());
        txtUbicacion.setText(puntoRecoleccionModel.getUbicacion());

        setTitle(R.string.title_form_punto_recoleccion);
        if(bundleExtra.containsKey(Data.ACCION_NUEVO)){
            btnAceptar.setText("Crear");
        } else if(bundleExtra.containsKey(Data.ACCION_ACTUALIZAR)){
            btnAceptar.setText("Actualizar");
        }

    }

    public static class Data {
        public static final String PUNTO_RECOLECCION_MODEL = "PUNTO_RECOLECCION_MODEL";
        public static final String BUNDLE = "BUNDLE";
        public static final String ACCION_NUEVO = "ACCION_NUEVO";
        public static final String ACCION_ACTUALIZAR = "ACCION_ACTUALIZAR";

    }

    public void onClickAceptar(View view){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Data.PUNTO_RECOLECCION_MODEL, this.puntoRecoleccionModel);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onClickCancelar(View view){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Data.PUNTO_RECOLECCION_MODEL, this.puntoRecoleccionModel);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
