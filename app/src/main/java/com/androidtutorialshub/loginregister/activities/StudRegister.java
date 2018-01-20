package com.androidtutorialshub.loginregister.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.androidtutorialshub.loginregister.R;
import com.androidtutorialshub.loginregister.helpers.InputValidation;
import com.androidtutorialshub.loginregister.model.Stud;
import com.androidtutorialshub.loginregister.model.StudUser;
import com.androidtutorialshub.loginregister.sql.DatabaseHelper;
import com.androidtutorialshub.loginregister.sql.StudDAO;

public class StudRegister extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = StudRegister.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutSname;
    private TextInputLayout textInputLayoutSuname;
    private TextInputLayout textInputLayoutSemail;
    private TextInputLayout textInputLayoutSpassword;
    private TextInputLayout textInputLayoutSconfirmPassword;

    private TextInputEditText textInputEditTextSname;
    private TextInputEditText textInputEditTextSuname;
    private TextInputEditText textInputEditTextSemail;
    private TextInputEditText textInputEditTextSpassword;
    private TextInputEditText textInputEditTextSconfirmPassword;

    private AppCompatButton appCompatSbuttonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation; //HAVENT ADD THE INFOS FOR STUDENT'S TABLE
    private DatabaseHelper databaseHelper;
    private StudUser studUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutSname = (TextInputLayout) findViewById(R.id.textInputLayoutSname);
        textInputLayoutSuname = (TextInputLayout) findViewById(R.id.textInputLayoutSuname);
        textInputLayoutSemail = (TextInputLayout) findViewById(R.id.textInputLayoutSemail);
        textInputLayoutSpassword = (TextInputLayout) findViewById(R.id.textInputLayoutSpassword);
        textInputLayoutSconfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutSconfirmPassword);

        textInputEditTextSname = (TextInputEditText) findViewById(R.id.textInputEditTextSname);
        textInputEditTextSuname = (TextInputEditText) findViewById(R.id.textInputEditTextSuname);
        textInputEditTextSemail = (TextInputEditText) findViewById(R.id.textInputEditTextSemail);
        textInputEditTextSpassword = (TextInputEditText) findViewById(R.id.textInputEditTextSpassword);
        textInputEditTextSconfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextSconfirmPassword);

        appCompatSbuttonRegister = (AppCompatButton) findViewById(R.id.appCompatSbuttonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);
    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatSbuttonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        studUser = new StudUser();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatSbuttonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                Intent intentRegister = new Intent(getApplicationContext(), StudLogin.class);
                startActivity(intentRegister);
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSname, textInputLayoutSname, getString(R.string.error_message_name))) {
            return;
        }
//ADD ONS ON USERNAME
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSuname, textInputLayoutSuname, getString(R.string.error_message_uname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSemail, textInputLayoutSemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextSemail, textInputLayoutSemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSpassword, textInputLayoutSpassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextSpassword, textInputEditTextSconfirmPassword,
                textInputLayoutSconfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkStudUser(textInputEditTextSemail.getText().toString().trim())) {

            studUser.setSname(textInputEditTextSname.getText().toString().trim());
            studUser.setSuname(textInputEditTextSuname.getText().toString().trim());
            studUser.setSemail(textInputEditTextSemail.getText().toString().trim());
            studUser.setSpassword(textInputEditTextSpassword.getText().toString().trim());

            databaseHelper.addStudUser(studUser);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextSname.setText(null);
        textInputEditTextSuname.setText(null);
        textInputEditTextSemail.setText(null);
        textInputEditTextSpassword.setText(null);
        textInputEditTextSconfirmPassword.setText(null);
        }
}
