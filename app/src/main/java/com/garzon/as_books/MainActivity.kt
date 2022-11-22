package com.garzon.as_books

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val title = findViewById<EditText>(R.id.title)
        val pageNumber = findViewById<EditText>(R.id.pageNumber)
        val author = findViewById<EditText>(R.id.author)
        val bookMark = findViewById<EditText>(R.id.bookMark)
        val type = findViewById<EditText>(R.id.type)

        registerButton.setOnClickListener{
            val book = Book(null, pageNumber.text.toString().toInt(), bookMark.text.toString().toInt(), type.text.toString(), author.text.toString(), title.text.toString(), false)
            val bookDao = BookDao(this)
            bookDao.insert(book)

            val bookList = Intent(this, BookListActivity::class.java)
            startActivity(bookList)
        }

    }
}