package com.softices.trainee.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TraineeApp.db";

    // User table name
    private static final String TABLE_USER = "table_user";

    // User Table Columns names
    public static final String COLUMN_USER_FIRSTNAME = "user_firstname";
    public static final String COLUMN_USER_LASTNAME = "user_lastname";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_USER_MOBILE_NUMBER = "user_mobile";
    public static final String COLUMN_USER_GENDER = "user_gender";


    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_FIRSTNAME + " TEXT,"
            + COLUMN_USER_LASTNAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT PRIMARY KEY,"
            + COLUMN_USER_PASSWORD + " TEXT ,"
            + COLUMN_USER_MOBILE_NUMBER + " TEXT ,"
            + COLUMN_USER_GENDER + " TEXT "
            + ")";

    public Context context;

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        Log.e("table created -- ", CREATE_USER_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);

        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create user record
     *
     * @param
     */
    public boolean addUser(String firstname, String lastname, String email,
                           String password, String mobile, String gender) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_FIRSTNAME, firstname);
            values.put(COLUMN_USER_LASTNAME, lastname);
            values.put(COLUMN_USER_EMAIL, email);
            values.put(COLUMN_USER_PASSWORD, password);
            values.put(COLUMN_USER_MOBILE_NUMBER, mobile);
            values.put(COLUMN_USER_GENDER, gender);

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            Log.e("addUser", e.toString());
        }
        return false;
    }

    //return user list
    public boolean updateuser(String firstname, String lastname, String email,
                              String password, String mobile, String gender) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_FIRSTNAME, firstname);
            values.put(COLUMN_USER_LASTNAME, lastname);
            values.put(COLUMN_USER_EMAIL, email);
            values.put(COLUMN_USER_PASSWORD, password);
            values.put(COLUMN_USER_MOBILE_NUMBER, mobile);
            values.put(COLUMN_USER_GENDER, gender);
            String selection = COLUMN_USER_EMAIL + "=?";
            String[] selectionarg = {email};
            //Updating Row
            db.update(TABLE_USER,
                    values,
                    selection,
                    selectionarg);

            db.close();
            return true;
        } catch (Exception e){
        }
        return false;
    }




    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public Cursor getUserData(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_FIRSTNAME,
                COLUMN_USER_LASTNAME,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_MOBILE_NUMBER,
                COLUMN_USER_GENDER,

        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        return cursor;
    }
}