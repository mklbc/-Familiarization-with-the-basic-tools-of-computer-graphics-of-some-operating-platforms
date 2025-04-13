package com.example.lab1_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class CustomView extends View {
    private final Paint paint;
    private final Path sunPath;
    private final Path trianglePath;
    private final Path sunRaysPath;

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        sunPath = new Path();
        trianglePath = new Path();
        sunRaysPath = new Path();
        precomputePaths();
    }

    private void precomputePaths() {
        int centerX = 600, centerY = 500;
        int sunRadius = 140;
        int rayLength = 180;
        int triangleSize = 160;


        sunPath.addCircle(centerX, centerY, sunRadius, Path.Direction.CW);


        for (int i = 0; i < 18; i++) {
            double angle = i * (2 * Math.PI / 18);
            float x1 = centerX + (float) (sunRadius * Math.cos(angle));
            float y1 = centerY - (float) (sunRadius * Math.sin(angle));
            float x2 = centerX + (float) (rayLength * Math.cos(angle));
            float y2 = centerY - (float) (rayLength * Math.sin(angle));
            sunRaysPath.moveTo(x1, y1);
            sunRaysPath.lineTo(x2, y2);
        }


        trianglePath.moveTo(centerX, centerY + 250);
        trianglePath.lineTo(centerX - triangleSize, centerY + 400);
        trianglePath.lineTo(centerX + triangleSize, centerY + 400);
        trianglePath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawColor(0xFFB0B0B0);


        paint.setColor(0xFF0000FF);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(sunPath, paint);


        paint.setColor(0xFFFF0000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawPath(sunRaysPath, paint);


        paint.setColor(0xFFFFFF00);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(trianglePath, paint);
    }
}
