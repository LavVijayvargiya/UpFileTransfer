package com.example.root.upfiletransfer;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText t;
    Button log,reg;
    private Session session;
    private final AppCompatActivity activity = LoginActivity.this;

//    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        databaseHelper = new DatabaseHelper(this);
        t = (EditText) findViewById(R.id.username);
        log = (Button) findViewById(R.id.logindd);
        reg = (Button) findViewById(R.id.register);
        log.setOnClickListener(this);
        reg.setOnClickListener(this);

        if(session.loggedIn()){
            startActivity(new Intent(LoginActivity.this , MainActivity.class));
            finish();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logindd:
                login();
                break;
            case R.id.register:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            default:

        }
    }

    private void login(){
        String username = t.getText().toString();
//        String pass = etPass.getText().toString();

        if(databaseHelper.getUser(username)){
            session.setLoggedIn(true);
            Intent i = new Intent(getApplicationContext() , SendReceive.class);
            i.putExtra("username" , username);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }

//    private void verifyFromSQLite() {
////        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
////            return;
////        }
////        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
////            return;
////        }
////        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
////            return;
////        }
//
//        if (databaseHelper.checkUser(t.getText().toString().trim())){
//
//
//            Intent accountsIntent = new Intent(activity, SendReceive.class);
////            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//            startActivity(accountsIntent);
//
//
//        } else {
//            // Snack Bar to show success message that record is wrong
////            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"Invalid Username",Toast.LENGTH_LONG);
//        }
//    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        t.setText(null);
//        textInputEditTextPassword.setText(null);
    }
}
