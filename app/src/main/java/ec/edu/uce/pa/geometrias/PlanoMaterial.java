package ec.edu.uce.pa.geometrias;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import ec.edu.uce.pa.utilidades.Funciones;

public class PlanoMaterial {
    private FloatBuffer bufferVertices;
    private FloatBuffer bufferColores;
    private FloatBuffer bufferNormales;
    private ByteBuffer bufferIndice;
    private final static int byteFlotante = 4;
    private final static int comPorVertices = 3;
    private final static int comPorColor = 4;
    public PlanoMaterial(){
        float[] vertices ={
                1.0f,-1.0f,-1.0f,
                1.0f,-1.0f,1.0f,
                -1.0f,-1.0f,1.0f,
                -1.0f,-1.0f,-1.0f
        };

        float[] colores ={
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,
                1.0f,0.0f,0.0f,1.0f,

        };
        byte[] indices = {
                0,1,2,
                0,2,3,
        };
        float[] normales ={
                0,1,0,
                0,1,0,
                0,1,0,
                0,1,0

        };
        bufferVertices = Funciones.generarBuffer(vertices);
        bufferColores = Funciones.generarBuffer(colores);
        bufferNormales = Funciones.generarBuffer(normales);

        bufferIndice = ByteBuffer.allocateDirect(indices.length);
        bufferIndice.order(ByteOrder.nativeOrder());
        bufferIndice.put(indices);
        bufferIndice.position(0);



    }
    public void dibujar(GL10 gl){
        gl.glFrontFace(gl.GL_CW);
        gl.glVertexPointer(comPorVertices,gl.GL_FLOAT,0,bufferVertices);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);

        bufferNormales.position(0);
        gl.glNormalPointer(gl.GL_FLOAT,0,bufferNormales);
        gl.glEnableClientState(gl.GL_NORMAL_ARRAY);

        bufferColores.position(0);
        gl.glColorPointer(comPorColor,gl.GL_FLOAT,0,bufferColores);
        gl.glEnableClientState(gl.GL_COLOR_ARRAY);

        gl.glDrawElements(gl.GL_TRIANGLES,6,gl.GL_UNSIGNED_BYTE, bufferIndice);

        gl.glDisableClientState(gl.GL_VERTEX_ARRAY);
        gl.glDisableClientState(gl.GL_COLOR_ARRAY);
        gl.glDisableClientState(gl.GL_NORMAL_ARRAY);
    }
}
