package com.himanshuhc.drugtracker.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicationDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medications.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "medications";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_RXCUI = "rxcui";
    public static final String COLUMN_IS_CUSTOM = "is_custom";

    public MedicationDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_RXCUI + " TEXT,"
                + COLUMN_IS_CUSTOM + " INTEGER,"
                + "psn TEXT,"
                + "synonym TEXT,"
                + "tty TEXT,"
                + "language TEXT,"
                + "suppress TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert Medication
    public boolean insertMedication(String name, String rxcui, boolean isCustom,
                                    String psn, String synonym, String tty, String language, String suppress) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if medication already exists
        String selection = COLUMN_NAME + " = ? AND " + COLUMN_RXCUI + " = ?";
        String[] selectionArgs = { name, rxcui };

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();

        if (exists) {
            db.close();
            return false; // Already exists
        }

        // Insert new medication
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_RXCUI, rxcui);
        values.put(COLUMN_IS_CUSTOM, isCustom ? 1 : 0);
        values.put("psn", psn);
        values.put("synonym", synonym);
        values.put("tty", tty);
        values.put("language", language);
        values.put("suppress", suppress);

        long result = db.insert(TABLE_NAME, null, values);
        db.close();

        return result != -1;
    }

    // Delete Medication by ID
    public void deleteMedication(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Get all medications
    public List<Medication> getAllMedications() {
        List<Medication> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String rxcui = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RXCUI));
                boolean isCustom = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_CUSTOM)) == 1;

                // New fields
                String psn = cursor.getString(cursor.getColumnIndexOrThrow("psn"));
                String synonym = cursor.getString(cursor.getColumnIndexOrThrow("synonym"));
                String tty = cursor.getString(cursor.getColumnIndexOrThrow("tty"));
                String language = cursor.getString(cursor.getColumnIndexOrThrow("language"));
                String suppress = cursor.getString(cursor.getColumnIndexOrThrow("suppress"));

                // Assuming you've updated the Medication model accordingly:
                list.add(new Medication(id, name, rxcui, isCustom, psn, synonym, tty, language, suppress));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }


    // Count custom medications
    public int getCustomDrugCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_IS_CUSTOM + "=1", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    // Count API medications
    public int getApiDrugCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_IS_CUSTOM + "=0", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }

    // Check if medication with given RxCUI already exists in the DB
    public boolean medicationExists(String rxcui) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COLUMN_RXCUI + " = ?",
                new String[]{rxcui});

        boolean exists = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                exists = cursor.getInt(0) > 0;
            }
            cursor.close();
        }
        db.close();
        return exists;
    }

}
