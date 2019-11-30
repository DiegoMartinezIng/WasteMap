package waste.map;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Pop_up extends AppCompatActivity {
    private TextView nombre_texto;
    private TextView direccion_texto;
    private TextView tipo_texto;
    private TextView persona_texto;
    private TextView nombre_programa_texto;
    private TextView horario_texto;
    private TextView correo_texto;
    private TextView categoria_texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        nombre_texto = findViewById(R.id.texto_nombre);
        direccion_texto = findViewById(R.id.texto_direccion);
        tipo_texto = findViewById(R.id.texto_tipo);
        persona_texto = findViewById(R.id.texto_persona);
        nombre_programa_texto = findViewById(R.id.texto_nombre_programa);
        horario_texto = findViewById(R.id.texto_horario);
        correo_texto = findViewById(R.id.texto_correo);
        categoria_texto = findViewById(R.id.texto_categoria);
        nombre_texto.setText(getIntent().getStringExtra("Nombre"));
        direccion_texto.setText(getIntent().getStringExtra("Direccion"));
        tipo_texto.setText(getIntent().getStringExtra("Tipo"));
        persona_texto.setText(getIntent().getStringExtra("Persona"));
        nombre_programa_texto.setText(getIntent().getStringExtra("Nombre_programa"));
        horario_texto.setText(getIntent().getStringExtra("Horario"));
        correo_texto.setText(getIntent().getStringExtra("Correo"));
        categoria_texto.setText(getIntent().getStringExtra("Categoria"));
        DisplayMetrics medidaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medidaVentana);
        int ancho = medidaVentana.widthPixels;
        int alto = medidaVentana.heightPixels;
        getWindow().setLayout((int) (ancho * 0.8), (int) (alto * 0.7));
    }
}