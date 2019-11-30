package waste.map;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Mapa.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
    public void iniciar(View view){
        Intent intent = new Intent(getApplicationContext(),Mapa.class);
        startActivity(intent);
    }
}
