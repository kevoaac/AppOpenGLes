package ec.edu.uce.pa.geometrias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class AstroTextura {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferTexturas;
    private final static int comPorVertices = 3;
    private int franjas, cortes;
    private int arrayTexturas[];

    public AstroTextura(int franjas, int cortes, float radio, float ejePolar){

        this.franjas = franjas;
        this.cortes = cortes;

        float [] vertices ;
        float [] texturas;

        int iVertice = 0;
        int iTextura = 0;


        vertices = new float[3 * ((cortes * 2 + 2) * franjas)];// *2 porque son dos triangulos para cada cuadrado y +2 de los vertices duplicados para los triangulos degenerados
        texturas = new float[2 * ((cortes * 2 + 2) * franjas)];
        int i, j;

        for(i = 0; i < franjas; i++)  {
            //empieza en -90 grados (-1.57 radianes) incrementa hasta +90 grados  (o +1.57 radianes)
            //Phi   --> angulo de latitud
            //Theta --> angulo de longitud

            //Valor del angulo para el primer cìrculo
            float phi0 = (float)Math.PI * ((i + 0) * (1.0f/(franjas)) - 0.5f);
            float cosPhi0 = (float)Math.cos(phi0);
            float sinPhi0 = (float)Math.sin(phi0);

            //Valor del angulo para el segundo cìrculo
            float phi1 = (float)Math.PI * ((i + 1 ) * (1.0f/(franjas)) - 0.5f); //i + 1
            float cosPhi1 = (float)Math.cos(phi1);
            float sinPhi1 = (float)Math.sin(phi1);

            float cosTheta, sinTheta;
            //Bucle para construir los cortes de la esfera
            //Longitudes
            for(j = 0; j < cortes; j++) {//AQUI RECORREMOS LOS CORTES
                float theta = (float)(-2.0f * Math.PI * j * (1.0/(cortes -1)));
                cosTheta = (float)Math.cos(theta);
                sinTheta = (float)Math.sin(theta);

                // Dibujar la esfera en duplas, pares de puntos
                vertices[iVertice+0] = radio * cosPhi0 * cosTheta;          //x
                vertices[iVertice+1] = radio * (sinPhi0 * ejePolar);    //y
                vertices[iVertice+2] = radio * (cosPhi0 * sinTheta);        //z

                vertices[iVertice+3] = radio * cosPhi1 * cosTheta;          //x'
                vertices[iVertice+4] = radio * (sinPhi1 * ejePolar);    //y'
                vertices[iVertice+5] = radio * (cosPhi1 * sinTheta);        //z'

//-------------------------------------------------------
                texturas[iTextura+0] = j * 1.0f/(cortes-1);          //s
                texturas[iTextura+1] = (i+0) * 1.0f/(franjas-1)*-1;//t
                texturas[iTextura+2] = j * 1.0f/(cortes-1);        //s´
                texturas[iTextura+3] = (i+1) * 1.0f/(franjas-1)*-1;//t'

//--------------------------------------------------------
                iVertice += 2*3;
                iTextura+=2*2;
            }

            vertices[iVertice+0] = vertices[iVertice+3];
            vertices[iVertice+3] = vertices[iVertice-3];
            vertices[iVertice+1] = vertices[iVertice+4];
            vertices[iVertice+4] = vertices[iVertice-2];
            vertices[iVertice+2] = vertices[iVertice+5];
            vertices[iVertice+5] = vertices[iVertice-1];
        }

        bufferVertices = Funciones.generarBuffer(vertices);
        bufferTexturas = Funciones.generarBuffer(texturas);
    }
    public void dibujar(GL10 gl, int indiceTextura) {
        gl.glFrontFace(gl.GL_CW);

        bufferVertices.position(0);
        gl.glVertexPointer(comPorVertices, gl.GL_FLOAT, 0, bufferVertices);
        gl.glTexCoordPointer(2, gl.GL_FLOAT, 0, bufferTexturas);

        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glEnableClientState(gl.GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[indiceTextura]);
        gl.glDrawArrays(gl.GL_TRIANGLE_STRIP, 0, franjas * cortes * 2);

        gl.glFrontFace(gl.GL_CCW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
    }

    public void habilitarTexturas (GL10 gl, int numeroTexturas){
        gl.glEnable(gl.GL_TEXTURE_2D);
        arrayTexturas = new int[numeroTexturas];
        gl.glGenTextures(1,arrayTexturas,0);//Genera una textura en OpenGL y guarda su identificación en el array arrayTexturas.
    }
    public void cargarImagenesTextura (GL10 gl, Context context, int idImagen, int indice){
        gl.glEnable(gl.GL_TEXTURE_2D);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idImagen);
        gl.glBindTexture(gl.GL_TEXTURE_2D, arrayTexturas[indice]);
        GLUtils.texImage2D(gl.GL_TEXTURE_2D, 0, bitmap, 0);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        bitmap.recycle();
    }
}