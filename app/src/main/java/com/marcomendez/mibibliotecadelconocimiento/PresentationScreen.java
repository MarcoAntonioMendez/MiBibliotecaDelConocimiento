package com.marcomendez.mibibliotecadelconocimiento;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class PresentationScreen extends AppCompatActivity {
    private static final String NAVIGATION_BAR_COLOR = "";
    private static final String STATUS_BAR_COLOR = "";
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation_screen);
        layout = findViewById(R.id.presentation_screen_relative_layout);
        getWindow().getDecorView().setBackgroundColor(Color.BLACK);
        getWindow().setNavigationBarColor(Color.BLACK);
        getWindow().setStatusBarColor(Color.BLACK);
    }
}
