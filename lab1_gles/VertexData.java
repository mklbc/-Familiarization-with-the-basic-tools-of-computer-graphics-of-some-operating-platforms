package com.example.lab1_gles;
public class VertexData {
    public static float[] createSunAndTriangle() {
        int N = 24;
        float radius = 0.3f;
        float rayLength = 0.5f;
        int rayCount = 12;
        float[] verts = new float[(N + 2 + 3 + rayCount * 2) * 3];

        // Güneş merkezi
        verts[0] = 0.0f;
        verts[1] = 0.0f;
        verts[2] = 0.0f;

        // Güneş gövdesi
        for (int i = 0; i <= N; i++) {
            double angle = i * 2.0 * Math.PI / N;
            verts[3 * (i + 1)] = (float) Math.cos(angle) * radius;
            verts[3 * (i + 1) + 1] = (float) Math.sin(angle) * radius;
            verts[3 * (i + 1) + 2] = 0.0f;
        }

        // Üçgen
        verts[3 * (N + 2)] = -0.2f;
        verts[3 * (N + 2) + 1] = -0.6f;
        verts[3 * (N + 2) + 2] = 0.0f;

        verts[3 * (N + 3)] = 0.2f;
        verts[3 * (N + 3) + 1] = -0.6f;
        verts[3 * (N + 3) + 2] = 0.0f;

        verts[3 * (N + 4)] = 0.0f;
        verts[3 * (N + 4) + 1] = -0.3f;
        verts[3 * (N + 4) + 2] = 0.0f;

        // Güneş ışınları
        int index = (N + 5) * 3;
        for (int i = 0; i < rayCount; i++) {
            double angle = i * 2.0 * Math.PI / rayCount;
            verts[index++] = (float) Math.cos(angle) * radius; // Başlangıç noktası
            verts[index++] = (float) Math.sin(angle) * radius;
            verts[index++] = 0.0f;

            verts[index++] = (float) Math.cos(angle) * rayLength; // Uç noktası
            verts[index++] = (float) Math.sin(angle) * rayLength;
            verts[index++] = 0.0f;
        }

        return verts;
    }
}

