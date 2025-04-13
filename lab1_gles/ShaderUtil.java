package com.example.lab1_gles;
import android.opengl.GLES32;

public class ShaderUtil {
    public static int createShaderProgram() {
        String vertexShaderCode = "#version 300 es\n" +
                "layout(location = 0) in vec3 vPosition;\n" +
                "void main() {\n" +
                "  gl_Position = vec4(vPosition, 1.0);\n" +
                "}";

        String fragmentShaderCode = "#version 300 es\n" +
                "precision mediump float;\n" +
                "out vec4 fragColor;\n" +
                "void main() {\n" +
                "  if (gl_FragCoord.y < 300.0) {\n" + // Alt kısım için
                "    fragColor = vec4(0.0, 0.5, 0.0, 1.0);\n" +
                "  } else {\n" + // Üst kısım için
                "    fragColor = vec4(1.0, 1.0, 0.0, 1.0);\n" +
                "  }\n" +
                "}";

        int vertexShader = GLES32.glCreateShader(GLES32.GL_VERTEX_SHADER);
        GLES32.glShaderSource(vertexShader, vertexShaderCode);
        GLES32.glCompileShader(vertexShader);

        int fragmentShader = GLES32.glCreateShader(GLES32.GL_FRAGMENT_SHADER);
        GLES32.glShaderSource(fragmentShader, fragmentShaderCode);
        GLES32.glCompileShader(fragmentShader);

        int shaderProgram = GLES32.glCreateProgram();
        GLES32.glAttachShader(shaderProgram, vertexShader);
        GLES32.glAttachShader(shaderProgram, fragmentShader);
        GLES32.glLinkProgram(shaderProgram);

        GLES32.glDeleteShader(vertexShader);
        GLES32.glDeleteShader(fragmentShader);

        return shaderProgram;
    }
}
