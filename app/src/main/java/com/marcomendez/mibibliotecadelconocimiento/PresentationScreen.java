package com.marcomendez.mibibliotecadelconocimiento;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.marcomendez.mibibliotecadelconocimiento.game_engine.GameObject2D;

public class PresentationScreen extends AppCompatActivity {
    public static final int ORIGINAL_SCREEN_WIDTH = 1080;
    public static final int ORIGINAL_SCREEN_HEIGHT = 2131;
    private static final int MI_TAG_NAME_ORIGINAL_WIDTH = 155;
    private static final int MI_TAG_NAME_ORIGINAL_HEIGHT = 71;
    private static final int BIBLIOTECA_TAG_NAME_ORIGINAL_WIDTH = 698;
    private static final int BIBLIOTECA_TAG_NAME_ORIGINAL_HEIGHT = 73;
    private static final int DEL_TAG_NAME_ORIGINAL_WIDTH = 227;
    private static final int DEL_TAG_NAME_ORIGINAL_HEIGHT = 71;
    private static final int CONOCIMIENTO_TAG_NAME_ORIGINAL_WIDTH = 864;
    private static final int CONOCIMIENTO_TAG_NAME_ORIGINAL_HEIGHT = 73;
    private static final int DELTA_TIME = 32;
    private static final float MI_TAG_NAME_ANIMATION_SPEED = 10;
    private static final float BIBLIOTECA_TAG_NAME_ANIMATION_SPEED = 5;
    private static final float DEL_TAG_NAME_ANIMATION_SPEED = 10;
    private static final float CONOCIMIENTO_TAG_NAME_ANIMATION_SPEED = 10;

    private RelativeLayout layout;
    private GameObject2D miTagName,delTagName,conocimientoTagName,bibliotecaTagName;
    private int screenWidth,screenHeight,miTagNameYPosLimit,bibliotecaTagNameXPosLimit;
    private float miTagNameAnimationSpeed,bibliotecaTagNameAnimationSpeed,delTagNameAnimationSpeed;
    private float conocimientoTagNameAnimationSpeed;
    private volatile boolean appHasFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_screen);
        layout = findViewById(R.id.presentation_screen_relative_layout);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);
        getWindow().setStatusBarColor(Color.BLACK);

        // Getting the screen's resolution of the current device
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        setWords();

        startGame();
    }

    private void startGame(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{Thread.sleep(DELTA_TIME);}catch(InterruptedException e){e.printStackTrace();}
                    if(appHasFocus){
                        update();
                        render();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void update(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(miTagName.getYPos() <= miTagNameYPosLimit){
                    miTagName.setYPos(miTagName.getYPos() + miTagNameAnimationSpeed);
                }

                if(bibliotecaTagName.getXPos() <= bibliotecaTagNameXPosLimit){
                    bibliotecaTagName.setXPos(bibliotecaTagName.getXPos() + bibliotecaTagNameAnimationSpeed);
                }
            }
        });
    }

    private void render(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {// Windows has focus
            appHasFocus = true;
        }else{// Windows does not have focus
            appHasFocus = false;
        }
    }

    //Methods called only in constructor
    private void setWords(){
        // Setting all the tags part of the title.
        int[] miIR = {R.drawable.mi_tag_name};
        int miTagNameWidth = screenWidth*MI_TAG_NAME_ORIGINAL_WIDTH/ORIGINAL_SCREEN_WIDTH;
        int miTagNameHeight = screenHeight*MI_TAG_NAME_ORIGINAL_HEIGHT/ORIGINAL_SCREEN_HEIGHT;
        int miTagNameXPos = (screenWidth/2)-(miTagNameWidth/2);
        int miTagNameYPos = 0;
        miTagNameYPosLimit = screenHeight/5;
        miTagNameAnimationSpeed = (((float)screenHeight)*MI_TAG_NAME_ANIMATION_SPEED)/
                ((float)ORIGINAL_SCREEN_HEIGHT);
        miTagName = new GameObject2D(this, miTagNameXPos, miTagNameYPos, miTagNameWidth,
                miTagNameHeight, miIR);


        int[] bibliotecaIR = {R.drawable.biblioteca_tag_name};
        int bibliotecaTagNameWidth = screenWidth*BIBLIOTECA_TAG_NAME_ORIGINAL_WIDTH/ORIGINAL_SCREEN_WIDTH;
        int bibliotecaTagNameHeight = screenHeight*BIBLIOTECA_TAG_NAME_ORIGINAL_HEIGHT/ORIGINAL_SCREEN_HEIGHT;
        int bibliotecaTagNameXPos = 0;
        int bibliotecaTagNameYPos = miTagNameYPosLimit + miTagNameHeight;
        bibliotecaTagNameXPosLimit = (screenWidth/2)-(bibliotecaTagNameWidth/2);
        bibliotecaTagNameAnimationSpeed = (((float)screenWidth)*BIBLIOTECA_TAG_NAME_ANIMATION_SPEED)/
                ((float)ORIGINAL_SCREEN_WIDTH);
        bibliotecaTagName = new GameObject2D(this, bibliotecaTagNameXPos, bibliotecaTagNameYPos,
                bibliotecaTagNameWidth, bibliotecaTagNameHeight,bibliotecaIR);


        int[] delIR = {R.drawable.del_tag_name};
        int delTagNameWidth = screenWidth*DEL_TAG_NAME_ORIGINAL_WIDTH/ORIGINAL_SCREEN_WIDTH;
        int delTagNameHeight = screenHeight*DEL_TAG_NAME_ORIGINAL_HEIGHT/ORIGINAL_SCREEN_HEIGHT;
        delTagName = new GameObject2D(this, 0, 0,
                delTagNameWidth, delTagNameHeight, delIR);


        int[] conocimientoIR = {R.drawable.conocimiento_tag_name};
        int conocimientoTagNameWidth = screenWidth*CONOCIMIENTO_TAG_NAME_ORIGINAL_WIDTH/ORIGINAL_SCREEN_WIDTH;
        int conocimientoTagNameHeight = screenHeight*CONOCIMIENTO_TAG_NAME_ORIGINAL_HEIGHT/ORIGINAL_SCREEN_HEIGHT;
        conocimientoTagName = new GameObject2D(this,0,0,
                conocimientoTagNameWidth,conocimientoTagNameHeight,conocimientoIR);

        layout.addView(miTagName);
        layout.addView(bibliotecaTagName);
        // I will uncomment when the words xPos and YPos are ready
        //layout.addView(delTagName);
        //layout.addView(conocimientoTagName);
    }
}
