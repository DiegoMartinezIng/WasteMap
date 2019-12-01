package com.example.wastecontrol.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.wastecontrol.fragmen.notasfragment.ListaNotasFragment;
import com.example.wastecontrol.persistencia.model.NotaModel;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.persistencia.respositorio.NotaRepository;
import com.example.wastecontrol.persistencia.respositorio.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.example.wastecontrol.R;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ListaNotasActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int CREAR_NOTA = 0x0001;
    private FloatingActionButton fab;
    private ListaNotasFragment listaNotasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        setTitle("Notas");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        listaNotasFragment = (ListaNotasFragment) getSupportFragmentManager().findFragmentById(R.id.frgListaNotas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Repository repository = NotaRepository.getInstance();
        repository.buscar( new NotaModel(),list -> {
            List<NotaModel> notaModelList = (List<NotaModel>) list;
            ListaNotasActivity.this.loadNotas(notaModelList);
        }, e -> {
            Toast.makeText(this,"Error inesperado", Toast.LENGTH_LONG).show();
        });
    }

    private void loadNotas(List<NotaModel> notaModelList) {
        listaNotasFragment.loadNotas(notaModelList);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,FormNotaActivity.class);
        startActivityForResult(intent, CREAR_NOTA);
    }
}
