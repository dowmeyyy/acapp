package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidtutorialshub.loginregister.R;

public class Selection extends AppCompatActivity {
    private final AppCompatActivity activity = Selection.this;

    private NestedScrollView nestedScrollView;

    private AppCompatButton prof;
    private AppCompatButton stud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        getSupportActionBar().hide();

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        stud = (AppCompatButton) findViewById(R.id.stud);
        prof = (AppCompatButton) findViewById(R.id.prof);


        stud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Selection.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Selection.this, StudRegister.class);
                startActivity(intent);
                finish();
                return;

            }
        });

    }
}
