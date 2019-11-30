package com.example.wastecontrol;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wastecontrol.activities.OpcionActivity;
import com.example.wastecontrol.persistencia.model.PuntoRecoleccionModel;
import com.example.wastecontrol.persistencia.respositorio.PuntoReciclajeRepository;
import com.example.wastecontrol.persistencia.respositorio.Repository;
import com.example.wastecontrol.servicios.LocationService;
import com.example.wastecontrol.util.AutenticacionUtil;
import com.example.wastecontrol.util.ObjetoParametros;
import com.example.wastecontrol.util.SystemUtil;
import com.example.wastecontrol.util.Utils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private static String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadPermission();
    }

    private void loadPermission() {
        List<String> permissions = Arrays.asList(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.FOREGROUND_SERVICE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                // Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        );
        SystemUtil.checkPermission(MainActivity.this,permissions);
    }

    public void onClickInicio(View view){
        Intent serviceIntent = new Intent(getApplicationContext(), LocationService.class);
        startService(serviceIntent);
        Intent intent = new Intent(getApplicationContext(), OpcionActivity.class);
        startActivity(intent);
        AutenticacionUtil.getInstace().inciarSesion("user@wastecontrol.org", "123456", new Consumer<Task<AuthResult>>(){

            @Override
            public void accept(Task authResultTask) {
                if(authResultTask.isSuccessful()){
                    Toast.makeText(MainActivity.this, R.string.login_OK, Toast.LENGTH_SHORT).show();
                }
            }
        });
        finish();

    }

    public void initDb(){
        int i = 0;
        List<ObjetoParametros> listObjetoParametros = new ArrayList<>();
        ObjetoParametros objetoParametros;
        InputStream inputStream = getResources().openRawResource(R.raw.db);
        try(JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream))) {
            jsonReader.beginArray();
            while(jsonReader.hasNext()){
                i++;
                jsonReader.beginObject();
                objetoParametros = new ObjetoParametros();
                while (jsonReader.hasNext()){
                    objetoParametros.set(jsonReader.nextName(), jsonReader.nextString());
                }
                listObjetoParametros.add(objetoParametros);
                Log.i(TAG, "("+i+") -> "+ objetoParametros.toString());
                jsonReader.endObject();
            }
            jsonReader.endArray();

        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "initDb: NO SE PUEDE CARGAR EL ARCHIVO ("+i+")");
        }

        Repository repository = PuntoReciclajeRepository.getInstance();

        List<PuntoRecoleccionModel> puntoRecoleccionModels = Utils.transformPuntoRecoleccionModels(listObjetoParametros);
        puntoRecoleccionModels.stream().forEach(puntoRecoleccionModel -> {
            repository.crear(puntoRecoleccionModel, o -> {
                Log.d(TAG, "CREADO Punto: "+o.toString());
            }, e -> {
                Log.d(TAG, "*** ERROR Punto: "+e.toString());
            });
            System.out.println(puntoRecoleccionModel.getLatitud()+":"+puntoRecoleccionModel.getLongitud());
        });
        Toast.makeText(this,"TERMIN EL INICIO DE DB", Toast.LENGTH_LONG).show();

    }
}
