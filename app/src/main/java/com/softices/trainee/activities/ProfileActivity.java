package com.softices.trainee.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.softices.trainee.database.DatabaseHelper;
import com.softices.traineeapp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileActivity extends AppCompatActivity {
    EditText editfistname, editlastname, editmobilenumber, editEmail, editPassword, editconformpassword;
    private DatabaseHelper databaseHelper;
    private Button btnsave;
    private RadioGroup radioGroup;
    private String selectradiobuttontex;
    private String fname, lname, email, pword, mobile, gender;
    private RadioButton rdbMale, rdbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        setDefaultData();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!firstname(editfistname.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "FIELD CANNOT BE EMPTY.", Toast.LENGTH_SHORT).show();
                } else if (!lastname(editlastname.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "FIELD CANNOT BE EMPTY.", Toast.LENGTH_SHORT).show();
                } else if (!mobilenumber(editmobilenumber.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "Mobile number must be in between 10 character long.", Toast.LENGTH_SHORT).show();
                } else if (!emailValidator(editEmail.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "Invalid Email.", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValidMethod(editPassword.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "Password must be in between 6-12 character long.", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValidMethod(editconformpassword.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, "Password must be in between 6-12 character long.", Toast.LENGTH_SHORT).show();
                } else {

                    int selectRadiobutton = radioGroup.getCheckedRadioButtonId();
                    if (selectRadiobutton != -1) {
                        RadioButton radioButton = (RadioButton) findViewById(selectRadiobutton);
                        selectradiobuttontex = radioButton.getText().toString();
                        boolean update = databaseHelper.updateuser(
                                editfistname.getText().toString(),
                                editlastname.getText().toString(),
                                editEmail.getText().toString(),
                                editPassword.getText().toString(),
                                editmobilenumber.getText().toString(),
                                selectradiobuttontex);
                        if (update) {
                            Toast.makeText(ProfileActivity.this, " Data updated ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Not updated data", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
            }
        });
    }


    private void setDefaultData() {
        Cursor cursor = databaseHelper.getUserData(userEmail());
        if (cursor.moveToFirst()) {
            fname = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_FIRSTNAME));
            lname = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_LASTNAME));
            email = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_EMAIL));
            pword = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_PASSWORD));
            mobile = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_MOBILE_NUMBER));
            gender = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_USER_GENDER));
        }
        cursor.close();
        editfistname.setText(fname);
        editlastname.setText(lname);
        editEmail.setText(email);
        editPassword.setText(pword);
        editmobilenumber.setText(mobile);

//        if (gender.equalsIgnoreCase("male"))
//            rdbMale.setChecked(true);
//        else
//            rdbFemale.setChecked(true);

    }

    private void init() {
        editfistname = findViewById(R.id.edit_firstname);
        editlastname = findViewById(R.id.edit_lastname);
        editmobilenumber = findViewById(R.id.edit_mobilenumber);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editconformpassword = findViewById(R.id.edit_conpassword);
        btnsave = (Button) findViewById(R.id.save_button);
        radioGroup = (RadioGroup) findViewById(R.id.ta_gender);
        rdbMale = (RadioButton) findViewById(R.id.rdbMale);
        rdbFemale = (RadioButton) findViewById(R.id.rdbFemale);
        databaseHelper = new DatabaseHelper(this);
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

    private String userEmail() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString("email", "");
    }


}

