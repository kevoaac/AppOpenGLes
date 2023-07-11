package ec.edu.uce.pa.GrupalAstros;

import static android.opengl.GLES10.GL_LIGHT0;
import static android.opengl.GLES10.GL_LIGHT1;
import static android.opengl.GLES10.GL_LIGHT2;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class RenderSistemaSolar implements GLSurfaceView.Renderer {
    private float vIncremento = 0f;
    private final static int LUZ0 = GL_LIGHT0;
    private final static int LUZ1 = GL_LIGHT1;
    private final static int LUZ2 = GL_LIGHT2;
    private TextRenderer textRenderer;
    private Planetas planetas;
    private EstrellasFondo estrellas;
    private AnillosPluton anillosUrano;
    private AnillosPluton anillosSturno;
    private float[] materialEstrellas ={1.0f,1.0f,1.0f,1.0f};
    private float[] materialCere = {0.5f, 0.5f, 0.5f, 1.0f};
    private float[] materialMakeMake = {0.6f, 0.1f, 0.1f, 1.0f};
    private float[] materialPluto = {0.9f, 0.3f, 0.1f, 1.0f};
    private float[] materialEuropa = {0.8f, 0.85f, 0.9f, 1.0f};
    private float[] materialLuna = {0.8f, 0.85f, 0.9f, 1.0f};
    private float[] materialCallisto = {0.7f, 0.7f, 0.7f, 1.0f};
    private float[] materialMercurio = {0.6f, 0.6f, 0.6f, 1.0f};
    private float[] materialGanymede = {0.6f, 0.6f, 0.6f, 1.0f};
    private float[] materialTitan = {0.8f, 0.5f, 0.2f, 1.0f};
    private float[] materialMarte = {0.8f, 0.2f, 0.1f, 1.0f};
    private float[] materialVenus = {0.9f, 0.8f, 0.2f, 1.0f};
    private float[] materialTierra = {0.0f, 0.4f, 1.0f, 1.0f};
    float[] materialKepler22b = {0.2f, 0.4f, 0.6f, 1.0f};
    float[] materialNeptuno = {0.0f, 0.0f, 0.545f, 1.0f};
    float[] materialUrano = {0.0f, 0.749f, 1.0f, 1.0f};
    float[] materialAnillosUrano = {0.753f, 0.753f, 0.753f, 1.0f};
    float[] materialSaturno = {0.933f, 0.910f, 0.667f, 1.0f};
    float[] materialAnillosSaturno = {0.753f, 0.753f, 0.753f, 1.0f};
    float[] materialJupiter = {0.894f, 0.800f, 0.600f, 1.0f};
    float[] materialSol = {1.0f, 1.0f, 0.0f, 1.0f};
    private float[] materialSiriusA = {0.9f, 0.9f, 0.95f, 1.0f};
    private float[] materialElnathAzul = {0.4f, 0.6f, 1.0f, 1.0f};
    private float[] materialElnathNaranja = {1.0f, 0.6f, 0.4f, 1.0f};
    private float[] materialPollux = {0.8f, 0.4f, 0.2f, 1.0f};
    private float[] materialArcturus = {1.0f, 0.6f, 0.2f, 1.0f};


    float[] posicionLuz0= {0.0f, -10.0f,-4f,1.0f};
    float[] luzAmarilla = {1f,1f,0.0f, 1.0f};
    float[] luzEstrellas = {0.8f,0.8f,0.8f, 1.0f};
    float[] luzCeres = {0.8f, 0.8f, 0.8f, 1.0f};
    private float[] luzPluto = {0.9f, 0.3f, 0.1f, 1.0f};
    private float[] luzMakeMake = {0.8f, 0.2f, 0.2f, 1.0f};
    private float[] luzEuropa = {0.8f, 0.85f, 0.9f, 1.0f};
    private float[] luzCallisto = {0.7f, 0.7f, 0.7f, 1.0f};
    private float[] luzLuna = {0.8f, 0.85f, 0.9f, 1.0f};
    private float[] luzMercurio = {0.6f, 0.6f, 0.6f, 1.0f};
    private float[] luzTitan = {0.8f, 0.5f, 0.2f, 1.0f};
    private float[] luzGanymede = {0.6f, 0.6f, 0.6f, 1.0f};
    private float[] luzMarte = {0.8f, 0.2f, 0.1f, 1.0f};
    private float[] luzVenus = {0.9f, 0.8f, 0.2f, 1.0f};
    private float[] luzTierra = {0.0f, 0.4f, 1.0f, 1.0f};
    float[] luzNeptuno = {0.0f, 0.0f, 0.545f, 1.0f};
    float[] luzKepler22b = {0.2f, 0.4f, 0.6f, 1.0f};
    float[] luzUrano = {0.0f, 0.749f, 1.0f, 1.0f};
    float[] luzAnillosUrano = {0.753f, 0.753f, 0.753f, 1.0f};
    float[] luzSaturno = {0.933f, 0.910f, 0.667f, 1.0f};
    float[] luzAnillosSaturno = {0.753f, 0.753f, 0.753f, 1.0f};
    float[] luzJupiter = {0.894f, 0.800f, 0.600f, 1.0f};
    float[] luzSol = {1.0f, 1.0f, 0.0f, 1.0f};
    private float[] luzElnathAzul = {0.4f, 0.6f, 1.0f, 1.0f};
    private float[] luzElnathNaranja = {1.0f, 0.6f, 0.4f, 1.0f};
    private float[] luzSiriusA = {0.9f, 0.9f, 0.95f, 1.0f};
    private float[] luzPollux = {0.8f, 0.4f, 0.2f, 1.0f};
    private float[] luzArcturus = {1.0f, 0.6f, 0.2f, 1.0f};

    private float [] spotDir1, spotDir2;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0f,0f,0f,1.0f);
        textRenderer = new TextRenderer(gl);
        planetas = new Planetas(50,50,1f,1f);
        estrellas = new EstrellasFondo(200);
        anillosUrano = new AnillosPluton(50, 1.36f, 1.5f);
        anillosSturno = new AnillosPluton(50,1.15f,1.5f);
        gl.glEnable(gl.GL_LIGHTING);
        gl.glEnable(gl.GL_DEPTH_TEST);
        gl.glEnable(LUZ0);
    }

    float l, r, b, t, n, f;

    @Override
    public void onSurfaceChanged(GL10 gl, int ancho, int alto) {
        float aspectRatio = (float) ancho / alto;
        // Definir las dimensiones del frustrum
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f / aspectRatio;
        float top = 1.0f / aspectRatio;
        float near = 1.0f;
        float far = 8000.0f;
        // Calcular la relación de aspect
        // Definir las dimensiones del frustrum
        gl.glViewport(0, 0, ancho, alto);
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();//
        gl.glFrustumf(left, right, bottom, top, near, far);

        l=left; r=right; b = bottom; t = top; n =near; f = far;

    }

    private float cameraX = 0.0f; // Posición inicial de la cámara en el eje X
    private float cameraZ = 0.0f; // Posición inicial de la cámara en el eje Z
    private float velocidadEnX = 0.01f; // Velocidad de camara en X
    private float velocidadEnZ = 0.015f; // Velocidad de camara en Z
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(gl.GL_MODELVIEW);
        gl.glLoadIdentity();

        gl.glLightfv(LUZ0, gl.GL_POSITION, Funciones.generarBuffer(posicionLuz0));
        gl.glLightfv(LUZ0, gl.GL_SPECULAR, Funciones.generarBuffer(luzAmarilla));


//ESTO LO COMENTE YO: K Andrade
//        spotDir2 = new float[]{0, 0, -1};
//        gl.glLightfv(LUZ2, gl.GL_SPOT_DIRECTION, FloatBuffer.wrap(spotDir2));
//        gl.glLightf(LUZ2, gl.GL_SPOT_CUTOFF, 15);
//        gl.glLightf(LUZ2, gl.GL_SPOT_EXPONENT, 0);


        //MOVIMIENTO CAMARA---------------------------------------------------
        cameraX += velocidadEnX ; // Mover la cámara en el eje X
        cameraZ += velocidadEnZ; // Mover la cámara en el eje Z
        GLU.gluLookAt(gl,
                cameraX , 0, (cameraZ),
                cameraX,0 ,-1,
                0,1,0
        );
        if(cameraX>6){//a 6 unidades
            velocidadEnX=0.018f;
            velocidadEnZ=0.015f;
        }
        if(cameraX>20){//a 6 unidades
            velocidadEnX=0.03f;
            velocidadEnZ=0.015f;
        }
        if(cameraX>48){//a 48 unidades
            velocidadEnX=0.05f;
            velocidadEnZ=0.005f;
        }

        if(cameraX>107){//a 48 unidades
            velocidadEnX=0.1f;
            velocidadEnZ=0.05f;
        }
        if(cameraX>187){//a 48 unidades
            velocidadEnX=0.1f;
            velocidadEnZ=0f;
        }
        if(cameraX>257){//a 48 unidades
            velocidadEnX=0.4f;
            velocidadEnZ=0.5f;
        }
        if(cameraX>637){//a 48 unidades
            velocidadEnX=0.6f;
            velocidadEnZ=0f;
        }
        if(cameraX>950){//a 937 Unidades
            velocidadEnX=1f;
            velocidadEnZ=0.8f;
        }
        if(cameraX>1837){//a 937 Unidades
            velocidadEnX=1.5f;
            velocidadEnZ=0f;
        }
        if(cameraX>3037){//a 937 Unidades
            velocidadEnX=2.5f;
            velocidadEnZ=4.5f;


        }
        if(cameraX>9037){//a 937 Unidades //SOL
            velocidadEnX=10f;
            velocidadEnZ=0f;
        }
        //--------------------------------------------------------------------


        //ESTRELLAS:
        gl.glPushMatrix();{
            gl.glTranslatef(0, 0, -8);
            gl.glScalef(0.25f, 0.25f, 0.25f);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialEstrellas, 0);
            gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, luzEstrellas, 0);
            estrellas.dibujar(gl);
        }gl.glPopMatrix();


        //PARA TODOS LOS PLANETAS
        gl.glPushMatrix();{
            gl.glTranslatef(0f, 0, -4);//TODA LA ESCENA

            //CERES:
            gl.glPushMatrix();
            {
                gl.glTranslatef(0.0f, 0.0f, -1f);
                gl.glScalef(1.0f, 1.0f, 1.0f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialCere, 0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, luzCeres, 0);
                planetas.dibujar(gl);

            }
            gl.glPopMatrix();


            //MAKE MAKE
            gl.glPushMatrix();
            {
                gl.glTranslatef(4.0f, 0.0f, -1.5f);
                gl.glScalef(1.5f, 1.5f, 1.5f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialMakeMake, 0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzMakeMake,0);
                planetas.dibujar(gl);
            }gl.glPopMatrix();

            //PLUTO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(10.0f, 0.0f, -3f);
                gl.glScalef(3.0f, 3.0f, 3.0f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialPluto, 0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzPluto,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();


            //EUROPA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(20.0f,0.0f,-3.9f);
                gl.glScalef(3.9f,3.9f,3.9f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialEuropa,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzEuropa,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //LUNA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(32.0f,0.0f,-4.68f);
                gl.glScalef(4.68f,4.68f,4.68f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialLuna,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzLuna,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //CALLISTO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(48.0f,0.0f,-7.95f);
                gl.glScalef(7.95f,7.95f,7.95f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialCallisto,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzCallisto,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //MERCURIO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(66.0f,0.0f,-7.95f);
                gl.glScalef(7.95f,7.95f,7.95f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialMercurio,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzMercurio,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //TITAN:
            gl.glPushMatrix();
            {
                gl.glTranslatef(85.0f,0.0f,-8.75f);
                gl.glScalef(8.75f,8.75f,8.75f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialTitan,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzTitan,0);
                planetas.dibujar(gl);

            } gl.glPopMatrix();

            //GANYMEDE:
            gl.glPushMatrix();
            {
                gl.glTranslatef(107.0f,0.0f,-9.62f);
                gl.glScalef(9.62f,9.62f,9.62f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialGanymede,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzGanymede,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //MARTE:
            gl.glPushMatrix();
            {
                gl.glTranslatef(137.0f,0.0f,-14.44f);
                gl.glScalef(14.44f,14.44f,14.44f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialMarte,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzMarte,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //VENUS:
            gl.glPushMatrix();
            {
                gl.glTranslatef(187.0f,0.0f,-28.88f);
                gl.glScalef(28.88f,28.88f,28.88f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialVenus,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzVenus,0);
                planetas.dibujar(gl);

            } gl.glPopMatrix();

            //TIERRA:
            gl.glPushMatrix();
            {
                gl.glTranslatef(257.0f,0.0f,-31.76f);
                gl.glScalef(31.76f,31.76f,31.76f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialTierra,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzTierra,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //KEPLER-22B:
            gl.glPushMatrix();
            {
                gl.glTranslatef(387.0f,0.0f,-76.24f);
                gl.glScalef(76.24f,76.24f,76.24f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialKepler22b,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzKepler22b,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //NEPTUNO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(637.0f,0.0f,-137.23f);
                gl.glScalef(137.23f,137.23f,137.23f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialNeptuno,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzNeptuno,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //URANO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(937.0f,0.0f,-137.23f);
                gl.glScalef(137.23f,137.23f,137.23f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialUrano,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzUrano,0);
                planetas.dibujar(gl);

                gl.glPushMatrix();
                {
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAnillosUrano, 0);
                    //gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, luzAnillosUrano, 0);
                    anillosUrano.dibujar(gl);

                }gl.glPopMatrix();

            }gl.glPopMatrix();

            //SATURNO:
            gl.glPushMatrix();
            {
                gl.glTranslatef(1837.0f,0.0f,-343.09f);
                gl.glScalef(343.09f,343.09f,343.09f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialSaturno,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzSaturno,0);
                planetas.dibujar(gl);
                gl.glPushMatrix();
                {
                    gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_AMBIENT, materialAnillosSaturno, 0);
                    //gl.glMaterialfv(gl.GL_FRONT_AND_BACK, gl.GL_EMISSION, luzAnillosSaturno, 0);
                    anillosSturno.dibujar(gl);

                }gl.glPopMatrix();
            }gl.glPopMatrix();

            //JUPITER:
            gl.glPushMatrix();
            {
                gl.glTranslatef(3037.0f,0.0f,-446.02f);
                gl.glScalef(446.02f,446.02f,446.02f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialJupiter,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzJupiter,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //SOL:
            gl.glPushMatrix();
            {
                //gl.glTranslatef(9037.0f,0.0f,-4460.27f);
                gl.glTranslatef(9037.0f,0.0f,+0f);
                gl.glScalef(4460.27f,4460.27f,4460.27f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialSol,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzSol,0);
                planetas.dibujar(gl);

            } gl.glPopMatrix();

            //SIRIUS A:
            gl.glPushMatrix();
            {
                //gl.glTranslatef(23037.0f,0.0f,-7582.45f);
                gl.glTranslatef(23037.0f,0.0f,+0f);
                gl.glScalef(7582.45f,7582.45f,7582.45f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialSiriusA,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzSiriusA,0);
                planetas.dibujar(gl);

            } gl.glPopMatrix();

            //ELNATH:
            gl.glPushMatrix();
            {
                //gl.glTranslatef(53037.0f,0.0f,-19714.39f);
                gl.glTranslatef(53037.0f,0.0f,+0f);
                gl.glScalef(19714.39f,19714.39f,19714.39f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialElnathNaranja,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzElnathAzul,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //POLLUX:
            gl.glPushMatrix();
            {
                //gl.glTranslatef(123037.0f,0.0f,-39428.79f);
                gl.glTranslatef(123037.0f,0.0f,+0f);
                gl.glScalef(39428.79f,39428.79f,39428.79f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialPollux,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzPollux,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

            //ARCTURUS:
            gl.glPushMatrix();
            {
                //gl.glTranslatef(303037.0f,0.0f,-118286.37f);
                gl.glTranslatef(303037.0f,0.0f,+0f);
                gl.glScalef(118286.37f,118286.37f,118286.37f);
                gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_AMBIENT,materialArcturus,0);
                //gl.glMaterialfv(gl.GL_FRONT_AND_BACK,gl.GL_EMISSION,luzArcturus,0);
                planetas.dibujar(gl);

            }gl.glPopMatrix();

        }gl.glPopMatrix();






        vIncremento+=0.65f;
    }
}
