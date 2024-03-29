attribute vec4 posVertexShader;
attribute vec3 colorVertex;
varying vec4 fragColorvertex;
uniform mat4 matrizProyeccion;
void main() {
    //gl_Position = vec4(posVertexShader,0.0,1.0); //La componente w  se encarga de la proyeccion
    gl_Position = matrizProyeccion * posVertexShader;
    fragColorvertex = vec4(colorVertex,1.0);
}
