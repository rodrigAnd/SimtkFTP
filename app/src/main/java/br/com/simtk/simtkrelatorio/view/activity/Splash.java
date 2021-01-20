package br.com.simtk.simtkrelatorio.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.com.simtk.simtkrelatorio.R;

public class Splash extends AppCompatActivity {


    int tempoEspera = 1000*3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide(); // ocultando a actionBar
        splash(); //chando a tela splash e passando a proxima tela
    }

    private void splash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent  = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, tempoEspera);

    }
}