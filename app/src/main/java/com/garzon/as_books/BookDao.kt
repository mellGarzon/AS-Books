package com.garzon.as_books


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class BookDao(context: Context) {
    var database = DbHelper(context)


    @SuppressLint("Range")
    private fun bookFromCursor(cursor: Cursor): Book {
        val id = cursor.getInt(cursor.getColumnIndex(BOOK_ID))
        val author = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR))
        val title = cursor.getString(cursor.getColumnIndex(BOOK_TITLE))
        val type = cursor.getString(cursor.getColumnIndex(BOOK_TYPE))
        val pages = cursor.getInt(cursor.getColumnIndex(BOOK_PAGES))
        val bookMark = cursor.getInt(cursor.getColumnIndex(BOOK_BOOKMARK))
        val isReadInt = cursor.getInt(cursor.getColumnIndex(BOOK_IS_READ))

        // 1 = true | 0 = false. Transform the isReadInt value to a bool.
        val isRead = isReadInt == 1

        return Book(id, pages, bookMark, type, author, title, isRead)
    }


    fun insert(book: Book): String {
        val db = database.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOK_ID, book.id)
        contentValues.put(BOOK_AUTHOR, book.author)
        contentValues.put(BOOK_TITLE, book.title)
        contentValues.put(BOOK_TYPE, book.type)
        contentValues.put(BOOK_PAGES, book.pageNumber)
        contentValues.put(BOOK_BOOKMARK, book.bookMark)
        contentValues.put(BOOK_IS_READ, book.isRead)

        val resp_id = db.insert(TABLE_BOOKS, null, contentValues)
        val msg = if(resp_id!=-1L){
            "Book Inserted Successfully"
        }else{
            "Error when Inserting Book"
        }
        db.close()
        return msg
    }


    fun update(book: Book) : String {
        val db = database.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(BOOK_ID, book.id)
        contentValues.put(BOOK_AUTHOR, book.author)
        contentValues.put(BOOK_TITLE, book.title)
        contentValues.put(BOOK_TYPE, book.type)
        contentValues.put(BOOK_PAGES, book.pageNumber)
        contentValues.put(BOOK_BOOKMARK, book.bookMark)
        contentValues.put(BOOK_IS_READ, book.isRead)

        val resp_id = db.insertWithOnConflict(TABLE_BOOKS, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        val msg = if(resp_id!=-1L){
            "Book Updated Successfully"
        }else{
            "Error when Inserting Book"
        }
        db.close()
        return msg
    }


    fun remove(book: Book) : Int {
        val db = database.writableDatabase
        return db.delete(TABLE_BOOKS, "id =?", arrayOf(book.id.toString()))
    }


    fun getAll(): ArrayList<Book>{
        Log.v("LOG","GetAll")
        val db = database.writableDatabase
        val sql = "SELECT * FROM $TABLE_BOOKS"
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()
        while (cursor.moveToNext()){
            val book = bookFromCursor(cursor)
            books.add(book)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "GET ${books.size}")
        return books
    }


    fun getByTitle(title: String): ArrayList<Book>{
        Log.v("LOG","getByName")
        val db = database.writableDatabase
        val sql = "SELECT * FROM $TABLE_BOOKS WHERE $BOOK_TITLE LIKE '%$title%'"
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()
        while (cursor.moveToNext()){
            val book = bookFromCursor(cursor)
            books.add(book)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "GET ${books.size}")
        return books
    }

    fun getByIsRead(boolValue: Boolean): ArrayList<Book>{
        val isRead = if (boolValue) 1 else 0

        Log.v("LOG","getByName")
        val db = database.writableDatabase
        val sql = "SELECT * FROM $TABLE_BOOKS WHERE $BOOK_IS_READ LIKE '%$isRead%'"
        val cursor = db.rawQuery(sql, null)
        val books = ArrayList<Book>()
        while (cursor.moveToNext()){
            val book = bookFromCursor(cursor)
            books.add(book)
        }
        cursor.close()
        db.close()
        Log.v("LOG", "GET ${books.size}")
        return books
    }

}