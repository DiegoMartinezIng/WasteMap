package com.example.wastecontrol.util;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.function.Consumer;

public final class AutenticacionUtil {
    private static final String TAG = AutenticacionUtil.class.getSimpleName();
    private static  AutenticacionUtil instance;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser = null;

    private  AutenticacionUtil(){};
    public static AutenticacionUtil getInstace(){
        if(instance == null){
            instance = new AutenticacionUtil();
            instance.inicializar();
        }

        return instance;
    }

    private void inicializar() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null){
                    Log.d(TAG, "onAuthStateChanged: login:"+currentUser.getEmail());
                    AutenticacionUtil.this.currentUser = currentUser;
                } else {
                    Log.d(TAG, "onAuthStateChanged: logout");
                    AutenticacionUtil.this.currentUser = null;
                }
            }
        };
        this.firebaseAuth.addAuthStateListener(this.authStateListener);
    }

    public Boolean isLogIn(){
        return this.currentUser != null;
    }

    public void crearCuenta(String userEmail, String userPassword, Consumer<Task<AuthResult>> handler){
        this.firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(task -> handler.accept(task));
    }

    public void inciarSesion(String userEmail, String userPassword, Consumer<Task<AuthResult>> handler){
        this.firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(task -> handler.accept(task));
    }

}
