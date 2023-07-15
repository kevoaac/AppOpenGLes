package ec.edu.uce.pa.activities;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ec.edu.uce.pa.GrupalAstros.RenderSistemaSolar;
import ec.edu.uce.pa.renderers.RenderCuadradoTextura;
import ec.edu.uce.pa.renderers.RenderSistemaSOOLAAR;
import ec.edu.uce.pa.renderers.RenderPiramideTextura;

public class ColorPantallaActivity extends AppCompatActivity {
    private GLSurfaceView view;
    public static float red = 0f;
    public static float green = 0f;
    public static float blue = 0f;
    public static float alpha = 0f;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new GLSurfaceView(this);//Permite manejo de los renderes a utilizar
        view.setEGLContextClientVersion(1);
//        view.setRenderer(new RenderColores());

        view.setRenderer(new RenderPiramideTextura(getApplicationContext()));
        //view.setRenderer(new RenderCuadradoTextura(getApplicationContext()));
        //view.setRenderer(new RenderBlender(getApplicationContext()));

        //Para ahorrar recursos cuando solo la geometria cambia
        //view.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        setContentView(view);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()){
            case MotionEvent.ACTION_UP:
                red = (float)Math.random();
                green = (float)Math.random();
                blue = (float)Math.random();
                alpha = (float)Math.random();
        }
        return super.onTouchEvent(e);
    }

    //Boton back de vista ColorPantalla
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ColorPantallaActivity.this, EjerciciosActivity.class );
        startActivity(intent);
        finish();

    }




}
