package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.R;

public class EjerciciosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ejercicios);

        Button btnColorPantalla = findViewById(R.id.btnOpenGL10);
        btnColorPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ColorPantallaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btnFiguras = findViewById(R.id.btnOpenGL20);
        btnFiguras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), Activity_Figuras.class);
                startActivity(intent);
                finish();
            }
        });


        Button btnRegresar = findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CAMBIO\n");
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }

    //Boton Back de Android
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EjerciciosActivity.this, EjerciciosActivity.class );
        startActivity(intent);
        finish();

    }
}
