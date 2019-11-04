package waste.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Noticias extends AppCompatActivity {
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        myWebView= findViewById(R.id.webLayout);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://bogota.gov.co/tag/contaminacion-ambiental");

    }
}
