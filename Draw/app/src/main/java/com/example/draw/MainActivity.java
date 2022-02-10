package com.example.draw;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape = LINE;
    Button blue, red, black;
    static int color = Color.RED;

    class Point{
        int startX;
        int startY;
        int stopX;
        int stopY;
        int color;
        int curShape;
        Paint paint;

        public Point(int startX, int startY,int stopX, int stopY, int color, int curShape, Paint paint)
        {
            this.startX = startX;
            this.startY = startY;
            this.stopX = stopX;
            this.stopY = stopY;
            this.color = color;
            this.curShape = curShape;
            this.paint = paint;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyGraphicView m = new MyGraphicView(this);
        LinearLayout draw_linear = findViewById(R.id.draw);
        draw_linear.addView(m);
        blue = (Button)findViewById(R.id.Blue);
        red = (Button)findViewById(R.id.Red);
        black = (Button)findViewById(R.id.Black);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.RED;
            }
        });
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLACK;
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.BLUE;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.optionmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.itemline:
                curShape = LINE;
                return true;
            case R.id.itemcircle:
                curShape = CIRCLE;
                return true;
            case R.id.itemRect:
                curShape = RECT;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyGraphicView extends View {
        int startX = -1, startY = -1, stopX = -1, stopY = -1;
        ArrayList<Point> points = new ArrayList<>();
        Point Drawing;
        public MyGraphicView(Context context) {super(context);}
        @Override
        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    break;
                case  MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                    points.add(Drawing);
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(color);
            Drawing = new Point(startX, startY, stopX, stopY, color, curShape, paint);

            for (Point num : points)
                draw(num, canvas);
            if (Drawing != null)
                draw(Drawing, canvas);
        }
        public void draw(Point point, Canvas canvas){
            switch (point.curShape){
                case LINE:
                    canvas.drawLine(point.startX, point.startY, point.stopX, point.stopY, point.paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(point.stopX - point.startX, 2) + Math.pow(point.stopY - point.startY, 2));
                    canvas.drawCircle(point.startX, point.startY, radius, point.paint);
                    break;
                case RECT:
                    Rect rect = new Rect(point.startX, point.startY, point.stopX, point.stopY);
                    canvas.drawRect(rect, point.paint);
                    break;
            }
        }
    }
}