package com.zang.quizapp.database

import android.content.Context
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import com.zang.quizapp.model.Category
import com.zang.quizapp.model.Question

class DBHelper(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VER) {
    companion object {
        var instance: DBHelper? = null
        val DB_NAME = "EDMTQuiz2019.db"
        val DB_VER = 1
        @Synchronized
        fun getInstance(context: Context): DBHelper {
            if (instance == null)
                instance = DBHelper(context)
            return instance!!
        }
    }

    /*get all category*/
    val allCategorys: MutableList<Category>
        get() {
            val db = instance!!.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM Category", null)
            val categorys = ArrayList<Category>()
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    val category = Category(
                        cursor.getInt(cursor.getColumnIndex("ID")),
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getString(cursor.getColumnIndex("Image"))
                    )
                    categorys.add(category)
                    cursor.moveToNext()
                }
            }
            cursor.close()
            db.close()
            return categorys
        }

    /*get all question by Category*/
    fun getQuestionByCategory(categoryId: Int): MutableList<Question> {
        val db = instance!!.writableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM Question WHERE categoryId=$categoryId ORDER BY RANDOM() LIMIT 30",
            null
        )
        val questionList = ArrayList<Question>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val question = Question(
                    cursor.getInt(cursor.getColumnIndex("ID")),
                    cursor.getString(cursor.getColumnIndex("QuestionText")),
                    cursor.getString(cursor.getColumnIndex("QuestionImage")),
                    cursor.getString(cursor.getColumnIndex("AnswerA")),
                    cursor.getString(cursor.getColumnIndex("AnswerB")),
                    cursor.getString(cursor.getColumnIndex("AnswerC")),
                    cursor.getString(cursor.getColumnIndex("AnswerD")),
                    cursor.getString(cursor.getColumnIndex("CorrectAnswer")),
                    if (cursor.getInt(cursor.getColumnIndex("IsImageQuestion")) == 0) java.lang.Boolean.FALSE else java.lang.Boolean.TRUE,
                    cursor.getInt(cursor.getColumnIndex("CategoryId"))
                )
                questionList.add(question)
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return questionList
    }
}