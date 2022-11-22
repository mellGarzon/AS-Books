package com.garzon.as_books

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {

        val sql="CREATE TABLE $TABLE_BOOKS ($BOOK_ID  INTEGER PRIMARY KEY " +
                "AUTOINCREMENT," +
                "$BOOK_AUTHOR TEXT," +
                "$BOOK_TITLE TEXT," +
                "$BOOK_TYPE TEXT," +
                "$BOOK_PAGES INTEGER," +
                "$BOOK_BOOKMARK INTEGER," +
                "$BOOK_IS_READ BOOL)"

        db.execSQL(sql)
        Log.e("LOG","Criando")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME)
        onCreate(db)
    }

}