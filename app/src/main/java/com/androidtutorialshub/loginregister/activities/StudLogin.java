package com.androidtutorialshub.loginregister.activities;

            import android.content.Intent;
            import android.os.Bundle;
            import android.support.design.widget.Snackbar;
            import android.support.design.widget.TextInputEditText;
            import android.support.design.widget.TextInputLayout;
            import android.support.v4.widget.NestedScrollView;
            import android.support.v7.app.AppCompatActivity;
            import android.support.v7.widget.AppCompatButton;
            import android.support.v7.widget.AppCompatTextView;
            import android.util.Log;
            import android.view.View;

            import com.androidtutorialshub.loginregister.R;
            import com.androidtutorialshub.loginregister.helpers.InputValidation;
            import com.androidtutorialshub.loginregister.sql.DatabaseHelper;

public class StudLogin extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = StudLogin.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutSemail;
    private TextInputLayout textInputLayoutSpassword;

    private TextInputEditText textInputEditTextSemail;
    private TextInputEditText textInputEditTextSpassword;

    private AppCompatButton appCompatSbuttonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_login);
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

        textInputLayoutSemail = (TextInputLayout) findViewById(R.id.textInputLayoutSemail);
        textInputLayoutSpassword = (TextInputLayout) findViewById(R.id.textInputLayoutSpassword);

        textInputEditTextSemail = (TextInputEditText) findViewById(R.id.textInputEditTextSemail);
        textInputEditTextSpassword = (TextInputEditText) findViewById(R.id.textInputEditTextSpassword);

        appCompatSbuttonLogin = (AppCompatButton) findViewById(R.id.appCompatSbuttonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatSbuttonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatSbuttonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), Trial.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSemail, textInputLayoutSemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextSemail, textInputLayoutSemail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextSpassword, textInputLayoutSpassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkStudUser(textInputEditTextSemail.getText().toString().trim()
                , textInputEditTextSpassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(activity, Trial.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextSemail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextSemail.setText(null);
        textInputEditTextSpassword.setText(null);
    }
}
