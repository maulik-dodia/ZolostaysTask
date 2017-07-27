package com.zolostaystask.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zolostaystask.models.User;


public class DBHelper extends SQLiteOpenHelper {

    //DB NAME
    private static final String DATABASE_NAME = "user_info";

    //DB VERSION
    private static final int DATABASE_VERSION = 1;

    //TABLE NAME
    public static final String TABLE_USER = "user";

    //KEYS
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_PASSWORD = "user_password";
    public static final String KEY_USER_PHONE = "user_phone";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_USER_EMAIL + " TEXT,"
                + KEY_USER_PASSWORD + " TEXT,"
                + KEY_USER_PHONE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public long registerUser(User user) {
        long registeredId = -11;
        if (!checkForExistingUser(user.getEmail())) {
            if (user != null) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(KEY_USER_NAME, user.getName());
                contentValues.put(KEY_USER_EMAIL, user.getEmail());
                contentValues.put(KEY_USER_PASSWORD, user.getPwd());
                contentValues.put(KEY_USER_PHONE, user.getPhone());
                SQLiteDatabase db = this.getWritableDatabase();
                registeredId = db.insert(TABLE_USER, null, contentValues);
                db.close();
                Log.e("mk", "Updated id-->" + user.getPwd());
            }
        }
        return registeredId;
    }

    public boolean checkForExistingUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_USER_EMAIL}, KEY_USER_EMAIL + "=?",
                new String[]{email}, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        }
        db.close();
        return false;
    }

    public boolean loginUser(String phone, String pwd) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER,
                new String[]{KEY_USER_PHONE, KEY_USER_PASSWORD}, KEY_USER_PHONE + "=? AND " + KEY_USER_PASSWORD + "=?",
                new String[]{phone, pwd}, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            return true;
        }
        db.close();
        return false;
    }

    public int updateUserPwd(String email, String newPwd) {
        int updatedId;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_PASSWORD, newPwd);
        updatedId = db.update(TABLE_USER, values, KEY_USER_EMAIL + " = ?", new String[]{email});
        Log.e("mk", "Updated id-->" + updatedId);
        db.close();
        return updatedId;
    }

    public String getUsername(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_USER_NAME}, KEY_USER_EMAIL + "=?",
                new String[]{email}, null, null, null, null);

        String username = null;
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        db.close();
        return username;
    }
}