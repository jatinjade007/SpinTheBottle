package com.squadf1.flip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    private View main;
    private ImageView bottle;
    private int lastAngle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.root);
        bottle=findViewById(R.id.img);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinTheBottle();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_spin :
                spinTheBottle();
                break;
            case R.id.action_zero :
                resetTheBottle();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void spinTheBottle() {
        int angle = RANDOM.nextInt(3600 - 360) + 360;
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        final Animation animRotate = new RotateAnimation(lastAngle == -1 ?
                0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animRotate.setDuration(2500);
        animRotate.setFillAfter(true);
        bottle.startAnimation(animRotate);
    }

    private void resetTheBottle() {
        float pivotX = bottle.getWidth() / 2;
        float pivotY = bottle.getHeight() / 2;
        final Animation animRotate = new RotateAnimation(lastAngle == -1 ?
                0 : lastAngle, 0, pivotX, pivotY);
        lastAngle = -1;
        animRotate.setDuration(2000);
        animRotate.setFillAfter(true);
        bottle.startAnimation(animRotate);
    }
}
