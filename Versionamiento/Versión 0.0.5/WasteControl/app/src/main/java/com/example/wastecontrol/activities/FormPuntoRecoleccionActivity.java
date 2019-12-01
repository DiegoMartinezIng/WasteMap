package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormPuntoRecoleccionActivity extends AppCompatActivity {

    private PuntoRecoleccionModel model;

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

    @BindView(R.id.txtCategoriaResiduo)
    EditText txtCategoriaResiduo;

    @BindView(R.id.txtTipoResiduo)
    EditText txtTipoResiduo;

    @BindView(R.id.txtNombreResiduo)
    EditText txtNombreResiduo;

    @BindView(R.id.txtUbicacion)
    EditText txtUbicacion;

    @BindView(R.id.txtHorario)
    EditText txtHorario;

    @BindView(R.id.txtProgramaPostconsumo)
    EditText txtProgramaPostconsumo;

    @BindView(R.id.txtPersonaContacto)
    EditText txtPersonaContacto;

    @BindView(R.id.txtEmail)
    EditText txtEmail;


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
        model = (PuntoRecoleccionModel) bundleExtra.getSerializable(Data.PUNTO_RECOLECCION_MODEL);
        txtDireccion.setText(model.getDireccion());
        txtCiudad.setText(model.getCiudad());
        txtDepartamento.setText(model.getDepartamento());
        txtPais.setText(model.getPais());
        txtUbicacion.setText(model.getUbicacion());

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
        model.setNombrePuntoRecoleccion(txtNombrePuntoRecoleccion.getText().toString());
        model.setDireccion(txtDireccion.getText().toString());
        model.setCiudad(txtCiudad.getText().toString());
        model.setDepartamento(txtDepartamento.getText().toString());
        model.setPais(txtPais.getText().toString());
        model.setCategoriaResiduo(txtCategoriaResiduo.getText().toString());
        model.setTipoResiduo(txtTipoResiduo.getText().toString());
        model.setNombreResiduo(txtNombreResiduo.getText().toString());
        model.setUbicacion(txtUbicacion.getText().toString());
        model.setHorario(txtHorario.getText().toString());
        model.setNombrePrograma(txtProgramaPostconsumo.getText().toString());
        model.setPersonaContacto(txtPersonaContacto.getText().toString());
        model.setEmail(txtEmail.getText().toString());

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Data.PUNTO_RECOLECCION_MODEL, this.model);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void onClickCancelar(View view){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Data.PUNTO_RECOLECCION_MODEL, this.model);
        intent.putExtra(Data.BUNDLE, bundle) ;
        setResult(Activity.RESULT_CANCELED, intent);
        finish();
    }
}
