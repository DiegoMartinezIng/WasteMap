package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wastecontrol.MainActivity;
import com.example.wastecontrol.R;
import com.example.wastecontrol.persistencia.model.Model;
import com.example.wastecontrol.persistencia.model.TipoReciclajeModel;
import com.example.wastecontrol.persistencia.respositorio.Repository;
import com.example.wastecontrol.persistencia.respositorio.TipoReciclajeRepository;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.function.Consumer;

public class OpcionActivity extends AppCompatActivity {

    private static final String TAG = OpcionActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion);
    }

    public void onClickUbicaion(View view){
        Intent intent = new Intent(this.getApplicationContext(), UbicacionActivity.class);
        startActivity(intent);
    }

    public void crear(View view){
        TipoReciclajeModel tipo = new TipoReciclajeModel(null, "LLANTA", "llantas");
        Repository repository = TipoReciclajeRepository.getInstance();
        repository.crear(tipo, o -> {
            if(o != null){
                Toast.makeText(OpcionActivity.this,"CREADO",Toast.LENGTH_LONG).show();
            }
        }, new Consumer<Exception>() {
            @Override
            public void accept(Exception e) {
                Log.e(OpcionActivity.TAG, "accept: "+e.getMessage(), e);
            }
        });

    }

    public void buscar(View view){
        TipoReciclajeModel filtro = new TipoReciclajeModel();
        filtro.setCodigo("LLANTA");
        Repository repository = TipoReciclajeRepository.getInstance();
        repository.buscar(filtro, new Consumer<List>() {
            @Override
            public void accept(List list) {
                if (list.size() > 0) {
                    Toast.makeText(OpcionActivity.this, "EXITO", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "Buscar: ------------- NO DATOS");
                }
            }
        }, new Consumer<Exception>() {
            @Override
            public void accept(Exception e) {
                Log.e(OpcionActivity.TAG, "Error buscar: "+e.getMessage(), e);
            }
        });

    }

    public void buscarRef(View view){
        TipoReciclajeModel filtro = new TipoReciclajeModel();
        filtro.setId( "kHff6XrDnma7Qqt7bmiW");
        TipoReciclajeRepository.getInstance().buscarReferencia(filtro, o -> {
            Log.d(TAG, "buscarRef: "+o.toString());
        }, e -> {
            Log.e(OpcionActivity.TAG, "Error buscar: ", (Throwable) e);
        });
    }
}
