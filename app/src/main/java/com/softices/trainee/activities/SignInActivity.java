package com.softices.trainee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softices.traineeapp.R;
import com.softices.trainee.database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {


    EditText edtPassword, editEmail;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        databaseHelper = new DatabaseHelper(this);

        TextView txtSignUp = findViewById(R.id.Newtotrainee_signup);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignInActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        TextView txtforgotpassword = findViewById(R.id.txt_Forgotpassword);
        txtforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        Button buttonsignin = findViewById(R.id.sign_in);
        editEmail = findViewById(R.id.edit_email);
        edtPassword = findViewById(R.id.edit_password);

        editEmail.setText("komal@t.com");
        edtPassword.setText("112233");


        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!emailValidator(editEmail.getText().toString())) {
                    Toast.makeText(SignInActivity.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValidMethod()) {
                    Toast.makeText(SignInActivity.this, "Password must be in between 6-12 character long.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean userLogin = databaseHelper.checkUser(
                            editEmail.getText().toString(),
                            edtPassword.getText().toString());

                    if (userLogin) {
                        saveData(editEmail.getText().toString(), true);
                        Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                    }
            }
        });
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validate password
    private boolean isPasswordValidMethod() {
        String yourString = edtPassword.getText().toString();
        if (yourString.length() > 5 && yourString.length() < 12)
            return true;
        else
            return false;
    }

    private void saveData(String email, boolean is_login) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("user_login", is_login);
        editor.putString("email", email);
        editor.commit();
    }


}