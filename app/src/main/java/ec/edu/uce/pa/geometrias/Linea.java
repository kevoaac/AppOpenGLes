package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.activities.Activity_Figuras;

public class Linea {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 2;
    private final static int comPorColor = 4;


    private int numPuntos = Activity_Figuras.numPrimitivas;//input Numero de primitavas

    public Linea(){
        float[] vertices ={
                //Line Strip y Hexagono
                -5.0f, 12.0f,
                -4.0f, 9.0f,
                -1.0f, 9.0f,
                0.0f, 12.0f,
                1.0f, 9.0f,
                5.0f, 9.0f,

                2.0f, 3.0f,
                3.5f, 0.0f,
                2.0f, -3.0f,
                -2.0f, -3.0f,
                -3.5f, 0.0f,
                -2.0f, 3.0f
        };

        float[] colores ={
                1.0f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,1.0f,0.0f,1.0f,
                0.0f,0.0f,1.0f,1.0f,
                1.0f,1.0f,0.0f,1.0f,

                0.5f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,0.5f,0.0f,1.0f,
                0.0f,0.0f,0.5f,1.0f,
                0.5f,0.5f,0.0f,1.0f,

                0.25f,0.0f,0.0f,1.0f,//posiciones desde 1.0f->0 hasta n
                0.0f,0.25f,0.0f,1.0f,
                0.0f,0.0f,0.25f,1.0f,
                0.25f,0.25f,0.0f,1.0f
        };


        ByteBuffer buffer = ByteBuffer.allocateDirect(vertices.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferVertices = buffer.asFloatBuffer();
        bufferVertices.put(vertices);
        bufferVertices.position(0); //Desde donde posicion de arrays comienza a dibujar

        buffer = ByteBuffer.allocateDirect(colores.length*byteFlotante);
        buffer.order(ByteOrder.nativeOrder());
        bufferColores = buffer.asFloatBuffer();
        bufferColores.put(colores);

    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);


        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);
        //gl.glColor4f(1.0f,0.0f,0.0f,1.0f);
        //gl.glDrawArrays(gl.GL_POINTS,0,4); //especifico desde donde comienza y cuantos dibuja

//          Podemos cambiar los puntos de color y redireccionar los puntos
//        gl.glColor4f(0.0f,0.0f,0.0f,1.0f);
//        gl.glDrawArrays(gl.GL_POINTS,1,1);
        gl.glLineWidth(25);
        gl.glDrawArrays(gl.GL_LINE_STRIP,0,6); //vertice 1

        gl.glDrawArrays(gl.GL_LINE_LOOP, 6, 6); //vertice 7


        gl.glFrontFace(gl.GL_CW);
        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);


    }
}
