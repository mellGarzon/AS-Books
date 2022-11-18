package com.garzon.as_books


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class BookDao(context: Context) {
    var database = DbHelper(context)


    fun insert(book: Book): String {
        val db = database.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOK_AUTHOR, book.author)
        contentValues.put(BOOK_TITLE, book.title)
        contentValues.put(BOOK_TYPE, book.type)
        contentValues.put(BOOK_PAGES, book.pageNumber)
        contentValues.put(BOOK_BOOKMARK, book.bookMark)
        contentValues.put(BOOK_IS_READ, book.isRead)

        var resp_id = db.insert(TABLE_BOOKS, null, contentValues)
        val msg = if(resp_id!=-1L){
            "Book Inserted Successfully"
        }else{
            "Error when Inserting Book"
        }
        db.close()
        return msg
    }

    fun update(book: Book) : Boolean{
        val db = database.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOK_AUTHOR, book.author)
        contentValues.put(BOOK_TITLE, book.title)
        contentValues.put(BOOK_TYPE, book.type)
        contentValues.put(BOOK_PAGES, book.pageNumber)
        contentValues.put(BOOK_BOOKMARK, book.bookMark)
        contentValues.put(BOOK_IS_READ, book.isRead)

       db.insertWithOnConflict(TABLE_BOOKS, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
       db.close()

        return true
    }



}