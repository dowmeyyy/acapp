package com.androidtutorialshub.loginregister.activities;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.fragment.ClassFragment;
import com.androidtutorialshub.loginregister.fragment.CustomFragment;
import com.androidtutorialshub.loginregister.fragment.InfoFragment;


public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayoutSt;
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggleSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerLayoutSt = (DrawerLayout) findViewById(R.id.drawerstudent);
        mToggleSt = new ActionBarDrawerToggle(this, mDrawerLayoutSt, R.string.open, R.string.close);
        mDrawerLayoutSt.addDrawerListener(mToggleSt);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new ClassFragment());
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myclass:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new ClassFragment());
                        fragmentTransaction.commit();
                        toolbar.setTitle("");
                        item.setChecked(true);
                        break;

                    case R.id.custom:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new CustomFragment());
                        fragmentTransaction.commit();
                        toolbar.setTitle("Profile");
                        item.setChecked(true);
                        break;


                    case R.id.info:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new InfoFragment());
                        fragmentTransaction.commit();
                        toolbar.setTitle("Notifications");
                        item.setChecked(true);
                        break;

                    case R.id.log_out:
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Log Out");
                        alertDialog.setMessage("Are you sure?");
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        break;

                }
                mDrawerLayoutSt = (DrawerLayout) findViewById(R.id.drawerstudent);
                mDrawerLayoutSt.closeDrawer(GravityCompat.START);
                return true;
            }
        } );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mToggleSt.syncState();
    }

}
