package com.example.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database_note";
    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";
    public static final String COLUMN_DATE = "date";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_BODY + " TEXT,"
                + COLUMN_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addNote(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,noteModel.getTitle());
        values.put(COLUMN_BODY,noteModel.getBody());
        values.put(COLUMN_DATE,noteModel.getDate());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<NoteModel> getAllNotes(){
        List<NoteModel> noteModelList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(Integer.parseInt(cursor.getString(0)));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setBody(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModelList.add(noteModel);
            } while (cursor.moveToNext());
        }
        return noteModelList;
    }
}
