package com.example.notetakingappone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.SegmentFinder;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBsqlite extends SQLiteOpenHelper {


    static  final  String DB_Name="NotesDB";
    static  final  String TABLE_NAME="Note";
    static  final  String COLUMN_ID="ID";
    static  final  String COLUMN_TITLE="Title";
    static  final  String COLUMN_CONTENT="Content";
    public DBsqlite(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                COLUMN_CONTENT + " TEXT)";
        db.execSQL(createTableQuery);
    }

    public Boolean Insertdata(String title,String conten)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COLUMN_TITLE,title);
        content.put(COLUMN_CONTENT,conten);
        long succ=db.insert(TABLE_NAME,null,content);
        return succ>0;
    }
    public ArrayList<Note> getdata()
    {
        ArrayList<Note> notelist=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor= db.rawQuery("select * from "+TABLE_NAME,null);
       while(cursor.moveToNext())
       {
           Note notes=new Note(cursor.getString(1),cursor.getString(2));
           notelist.add(notes);
       }
       return notelist;

    }
    public String searchNote(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CONTENT + " FROM " + TABLE_NAME + " WHERE " + COLUMN_TITLE + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{text});
        String result = null;

        if (cursor.moveToNext()) {
            int columnIndex0 = cursor.getColumnIndex(COLUMN_CONTENT);

            result = cursor.getString(columnIndex0);
        }

        cursor.close();
        db.close();

        return result;
    }
    public boolean Updatedata(String title,String content)
    {
        String whereClause = COLUMN_TITLE + " = ?";
        String[] whereArgs = {title};
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contents=new ContentValues();
        contents.put(COLUMN_TITLE,title);
        contents.put(COLUMN_CONTENT,content);
        int yes= db.update(TABLE_NAME,contents,whereClause,whereArgs);
        return  yes>0;
    }
    public boolean deletedata(String title)
    {
        String whereClaus=COLUMN_TITLE + " = ?";
        String[] whereArgs={title};
        SQLiteDatabase db=this.getWritableDatabase();
        int yes=db.delete(TABLE_NAME,whereClaus,whereArgs);
        return  yes>0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
      db.execSQL("Drop table if Exists "+TABLE_NAME);
      onCreate(db);
    }
}
