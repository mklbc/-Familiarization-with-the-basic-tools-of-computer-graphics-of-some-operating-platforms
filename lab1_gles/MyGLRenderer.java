package com.example.lab1_gles;
import android.opengl.GLES32;
import android.opengl.GLSurfaceView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private int shaderProgram;
    private int VAO;
    private final float[] vertices = VertexData.createSunAndTriangle();
    private FloatBuffer vertexBuffer;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES32.glClearColor(0.5f, 0.8f, 1.0f, 1.0f); // Açık Mavi Arka Plan
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        shaderProgram = ShaderUtil.createShaderProgram();
        int[] vao = new int[1];
        GLES32.glGenVertexArrays(1, vao, 0);
        VAO = vao[0];
        GLES32.glBindVertexArray(VAO);

        int[] vbo = new int[1];
        GLES32.glGenBuffers(1, vbo, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vbo[0]);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, vertices.length * 4, vertexBuffer, GLES32.GL_STATIC_DRAW);
        GLES32.glVertexAttribPointer(0, 3, GLES32.GL_FLOAT, false, 3 * 4, 0);
        GLES32.glEnableVertexAttribArray(0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0);
        GLES32.glBindVertexArray(0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES32.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
        GLES32.glUseProgram(shaderProgram);
        GLES32.glBindVertexArray(VAO);

        // Güneşi çiz
        GLES32.glDrawArrays(GLES32.GL_TRIANGLE_FAN, 0, 26);

        // Üçgeni çiz
        GLES32.glDrawArrays(GLES32.GL_TRIANGLES, 26, 3);

        // Işınları çiz (GL_LINES kullanarak)
        GLES32.glDrawArrays(GLES32.GL_LINES, 29, 24);

        GLES32.glBindVertexArray(0);
    }

}
