package com.example.doubletap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        TextView text = findViewById(R.id.textView);

        GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            Integer counter = 0;
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                counter++;
                text.setText(counter.toString());
                button.setBackgroundColor(generateRandomLightColor());
                return true;
            }
        });

        button.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private int generateRandomLightColor() {
        Random random = new Random();
        int r = random.nextInt(256) / 2 + 128; // [128, 255]
        int g = random.nextInt(256) / 2 + 128; // [128, 255]
        int b = random.nextInt(256) / 2 + 128; // [128, 255]
        return Color.rgb(r, g, b);
    }
}