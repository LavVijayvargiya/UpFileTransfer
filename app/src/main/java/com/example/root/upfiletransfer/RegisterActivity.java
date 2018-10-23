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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

//    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    Button b,b2;
    EditText t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        databaseHelper = new DatabaseHelper(this);
        t = (EditText) findViewById(R.id.usernamer);
        b = (Button) findViewById(R.id.registerr);
        b2 = (Button) findViewById(R.id.loginr);
        b.setOnClickListener(this);
        b2.setOnClickListener(this);

        user = new User();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.registerr:
                register();
                break;

            case R.id.loginr:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
                break;
                default:
        }
    }

    private void register(){
        String username = t.getText().toString();
//        String pass = etPass.getText().toString();
        if(username.isEmpty()){
            displayToast("Username/password field empty");
        }else{
            databaseHelper.addUser(username);
            displayToast("User registered : " + username);
            finish();
        }
    }

//    private void postDataToSQLite() {
//
//        if (!databaseHelper.checkUser(t.getText().toString().trim())) {
//
//            user.setName(t.getText().toString().trim());
////            user.setEmail(textInputEditTextEmail.getText().toString().trim());
////            user.setPassword(textInputEditTextPassword.getText().toString().trim());
//
//            databaseHelper.addUser(user);
//
//            // Snack Bar to show success message that record saved successfully
////            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
//            Toast.makeText(getApplicationContext(),"Success" , Toast.LENGTH_LONG);
//            emptyInputEditText();
//
//
//        } else {
//            // Snack Bar to show error message that record already exists
//            Toast.makeText(getApplicationContext(),"Username Exists" , Toast.LENGTH_LONG);
//        }
//
//
//    }

//    private void emptyInputEditText() {
//        t.setText(null);
////        textInputEditTextEmail.setText(null);
////        textInputEditTextPassword.setText(null);
////        textInputEditTextConfirmPassword.setText(null);
//    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
