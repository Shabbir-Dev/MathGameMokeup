package com.shabbir.mathgame;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shabbir Ali on 27/10/2020.
 */
public class GameHelper extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 13;

    private static final String DATABASE_NAME = "mathsone";
    private static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";

    private SQLiteDatabase dbase;

    public GameHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        QuestionModel q1 = new QuestionModel("4+3 = ?", "7", "8", "6", "7");
        this.addQuestion(q1);
        QuestionModel q2 = new QuestionModel("1+19 = ?", "18", "19", "20", "20");
        this.addQuestion(q2);
        QuestionModel q3 = new QuestionModel("11-4 = ?", "6", "7", "8", "7");
        this.addQuestion(q3);
        QuestionModel q4 = new QuestionModel("4+8 = ?", "12", "13", "14", "12");
        this.addQuestion(q4);
        QuestionModel q5 = new QuestionModel("4-2 = ?", "1", "3", "2", "2");
        this.addQuestion(q5);
        QuestionModel q6 = new QuestionModel("0+1 = ?", "1", "0", "10", "1");
        this.addQuestion(q6);
        QuestionModel q7 = new QuestionModel("10-10 = ?", "0", "9", "1", "0");
        this.addQuestion(q7);
        QuestionModel q8 = new QuestionModel("4+5 = ?", "8", "7", "9", "9");
        this.addQuestion(q8);
        QuestionModel q9 = new QuestionModel("2+4 = ?", "6", "7", "5", "6");
        this.addQuestion(q9);
        QuestionModel q10 = new QuestionModel("7-5 = ?", "3", "2", "6", "2");
        this.addQuestion(q10);
        QuestionModel q11 = new QuestionModel("7-2 = ?", "7", "6", "5", "5");
        this.addQuestion(q11);
        QuestionModel q12 = new QuestionModel("2+6 = ?", "8", "7", "5", "8");
        this.addQuestion(q12);
        QuestionModel q13 = new QuestionModel("1+5 = ?", "7", "6", "5", "6");
        this.addQuestion(q13);
        QuestionModel q14 = new QuestionModel("12-10 = ?", "1", "2", "3", "2");
        this.addQuestion(q14);
        QuestionModel q15 = new QuestionModel("13+1 = ?", "14", "15", "16", "14");
        this.addQuestion(q15);
        QuestionModel q16 = new QuestionModel("2-1 = ?", "2", "1", "0", "1");
        this.addQuestion(q16);
        QuestionModel q17 = new QuestionModel("6-6 = ?", "6", "12", "0", "0");
        this.addQuestion(q17);
        QuestionModel q18 = new QuestionModel("5-1 = ?", "4", "3", "2", "4");
        this.addQuestion(q18);
        QuestionModel q19 = new QuestionModel("3+3 = ?", "6", "7", "5", "6");
        this.addQuestion(q19);
        QuestionModel q20 = new QuestionModel("4+2 = ?", "6", "7", "5", "6");
        this.addQuestion(q20);
        QuestionModel q21 = new QuestionModel("6-5 = ?", "5", "4", "1", "1");
        this.addQuestion(q21);
        // END
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);

        onCreate(db);
    }


    public void addQuestion(QuestionModel quest) {


        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());


        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<QuestionModel> getAllQuestions() {
        List<QuestionModel> quesList = new ArrayList<QuestionModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            QuestionModel quest = new QuestionModel();
            quest.setID(cursor.getInt(0));
            quest.setQUESTION(cursor.getString(1));
            quest.setANSWER(cursor.getString(2));
            quest.setOPTA(cursor.getString(3));
            quest.setOPTB(cursor.getString(4));
            quest.setOPTC(cursor.getString(5));

            quesList.add(quest);
        }

        return quesList;
    }


}
