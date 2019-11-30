package waste.map;

import android.os.Bundle;

import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;



import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class pqr extends AppCompatActivity {
    private EditText nombre_txt;
    private EditText correo_txt;
    private EditText Celular_txt;
    private EditText Dirreccion_txt;
    private EditText comentarios_txt;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pqr);
        nombre_txt = findViewById(R.id.Nombre);
        correo_txt = findViewById(R.id.Correo);
        Celular_txt = findViewById(R.id.Celular);
        Dirreccion_txt = findViewById(R.id.Dirreccion);
        comentarios_txt = findViewById(R.id.Comentarios);
    }

    public void enviar(View view) {
        Map<String, Object> user = new HashMap<>();
        String nombre = nombre_txt.getText().toString();
        user.put("Nombre", nombre);
        String correo = correo_txt.getText().toString();
        user.put("Correo", correo);
        String celular = Celular_txt.getText().toString();
        user.put("Celular", celular);
        String dirreccion = Dirreccion_txt.getText().toString();
        user.put("Dirreccion", dirreccion);
        String comentarios = comentarios_txt.getText().toString();
        user.put("Comentarios", comentarios);


// Add a new document with a generated ID
        db.collection("Formulario_PQR")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), documentReference.getId(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }
}
