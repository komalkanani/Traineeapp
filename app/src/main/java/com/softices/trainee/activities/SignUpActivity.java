package com.softices.trainee.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softices.traineeapp.R;
import com.softices.trainee.database.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    EditText editfistname, editlastname, editmobilenumber, editEmail, editPassword, editconformpassword;
    private DatabaseHelper databaseHelper;
    private TextView txtalredyhaveaccout;
    private Button btnSignUp;
    private RadioGroup radioGroup;
    private String selectradiobuttontex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        txtalredyhaveaccout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstname(editfistname.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "FIELD CANNOT BE EMPTY.", Toast.LENGTH_SHORT).show();
                } else if (!lastname(editlastname.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "FIELD CANNOT BE EMPTY.", Toast.LENGTH_SHORT).show();
                } else if (!mobilenumber(editmobilenumber.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Mobile number must be in between 10 character long.", Toast.LENGTH_SHORT).show();
                } else if (!emailValidator(editEmail.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValidMethod(editPassword.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password must be in between 6-12 character long.", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValidMethod(editconformpassword.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Password must be in between 6-12 character long.", Toast.LENGTH_SHORT).show();
                } else {

                    int selectRadiobutton = radioGroup.getCheckedRadioButtonId();
                    if (selectRadiobutton != -1) {
                        RadioButton radioButton = (RadioButton) findViewById(selectRadiobutton);
                        selectradiobuttontex = radioButton.getText().toString();
                    }
                    boolean userLogin = databaseHelper.addUser(
                            editfistname.getText().toString(),
                            editlastname.getText().toString(),
                            editEmail.getText().toString(),
                            editPassword.getText().toString(),
                            editmobilenumber.getText().toString(),
                            selectradiobuttontex
                    );

                    if (userLogin) {
                        Toast.makeText(SignUpActivity.this, "User Created Successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void init() {
        editfistname = findViewById(R.id.edit_firstname);
        editlastname = findViewById(R.id.edit_lastname);
        editmobilenumber = findViewById(R.id.edit_mobilenumber);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editconformpassword = findViewById(R.id.edit_conpassword);
        txtalredyhaveaccout = findViewById(R.id.txt_alredyhaveaccout);
        btnSignUp = (Button) findViewById(R.id.sign_up);
        radioGroup = (RadioGroup) findViewById(R.id.ta_gender);
        databaseHelper = new DatabaseHelper(this);


        editfistname.setText("komal");
        editlastname.setText("Talaviya");
        editmobilenumber.setText("1234567891");
        editEmail.setText("komal@t.com");
        editPassword.setText("112233");
        editconformpassword.setText("112233");

    }

    public boolean firstname(String firstname) {
        if (firstname.length() > 0)
            return true;
        else return false;
    }

    public boolean lastname(String lastname) {
        if (lastname.length() > 0)
            return true;
        else return false;
    }

    public boolean emailValidator(String email) {

        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean mobilenumber(String mobilenumber) {
        Pattern pattern;
        Matcher matcher;
        final String mobilenumber_PATTERN = "[0-9]{10}";
        pattern = Pattern.compile(mobilenumber_PATTERN);
        matcher = pattern.matcher(mobilenumber);
        return matcher.matches();
    }

    // Validate password
    private boolean isPasswordValidMethod(String yourString) {
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
