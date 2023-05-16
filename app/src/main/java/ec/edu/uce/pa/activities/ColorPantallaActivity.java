package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.renderers.RenderColores;

public class ColorPantallaActivity extends AppCompatActivity {

    private GLSurfaceView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new GLSurfaceView(this);//Permite manejo de los renderes a utilizar
        view.setEGLContextClientVersion(1);
        view.setRenderer(new RenderColores());
        setContentView(view);

    }

    //Boton back de vista ColorPantalla
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ColorPantallaActivity.this, PrincipalActivity.class );
        startActivity(intent);
        finish();

    }
}
