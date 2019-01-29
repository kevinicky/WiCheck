package id.rendesvouz.wicheckapps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Vector;

import id.rendesvouz.wicheckapps.Model.Colors;
import id.rendesvouz.wicheckapps.Model.Questions;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public Vector<Colors> getColorName(){
        Vector<Colors> colors = new Vector<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Color", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Colors temp = new Colors();
                temp.setR(cursor.getInt(0));
                temp.setG(cursor.getInt(1));
                temp.setB(cursor.getInt(2));
                temp.setColorName(cursor.getString(3));
                temp.setColorID(cursor.getInt(4));

                colors.add(temp);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return colors;
    }

    public Vector<Questions> getQuestion(int colorID){
        Vector<Questions> questions = new Vector<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Question WHERE Color = '" + colorID +  "'", null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Questions temp = new Questions();
                temp.setColor(cursor.getInt(1));
                temp.setPertayaan(cursor.getString(2));
                temp.setJawabanYes(cursor.getString(3));
                temp.setJawabanNo(cursor.getString(4));
                temp.setStep(cursor.getInt(5));
                temp.setAnswerYes(cursor.getString(6));
                temp.setAnswerNo(cursor.getString(7));

                questions.add(temp);
                cursor.moveToNext();
            }
        }

        cursor.close();
        return questions;
    }

    public Cursor ViewResult(){
        SQLiteDatabase dbRead = openHelper.getReadableDatabase();
        String query = "select R,G,B,Time,Status from Record";
        Cursor cursor = dbRead.rawQuery(query,null);

        return cursor;
    }

    public void addRecord(String R, String G, String B, String time, String status){
        db.execSQL("INSERT INTO Record (R, G, B, Time, Status) VALUES ('" + R + "', '" + G + "', '" + B + "', '" + time + "', '" + status + "')");
    }


}