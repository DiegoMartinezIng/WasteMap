package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.NotaModel;
import com.example.wastecontrol.persistencia.respositorio.NotaRepository;

import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormNotaActivity extends AppCompatActivity {

    @BindView(R.id.txtTitulo)
    EditText txtTitulo;

    @BindView(R.id.txtContenido)
    EditText txtContenido;

    @BindView(R.id.btnCrearNota)
    Button btnCrearNota;

    NotaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nota);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        model = new NotaModel();
    }

    public void onClickCrearNota(View view){

        model.setTitulo(txtTitulo.getText().toString());
        model.setContenido(txtContenido.getText().toString());
        model.setFecha(Calendar.getInstance().getTime());

        NotaRepository.getInstance().crear(model, o -> {
            NotaModel notaModel = (NotaModel) o;
            FormNotaActivity.this.successDialog("Nota creada exitosamente").show();
            System.out.println(o.toString());
        },e -> {
            Toast.makeText(this, "error: " + e.toString(), Toast.LENGTH_LONG).show();
        });
    }

    public static class Data {
        public static final String NOTA_MODEL = "NOTA_MODEL";
        public static final String BUNDLE = "BUNDLE";
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
