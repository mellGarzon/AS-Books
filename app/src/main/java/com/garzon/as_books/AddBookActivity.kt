package com.garzon.as_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AddBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val title = findViewById<EditText>(R.id.title)
        val pageNumber = findViewById<EditText>(R.id.pageNumber)
        val author = findViewById<EditText>(R.id.author)
        val bookMark = findViewById<EditText>(R.id.bookMark)
        val type = findViewById<EditText>(R.id.type)

        registerButton.setOnClickListener{
            val book = Book(null, pageNumber.text.toString().toInt(), bookMark.text.toString().toInt(), type.text.toString(), author.text.toString(), title.text.toString(), true)
            val bookDao = BookDao(this)
            bookDao.insert(book)

            onBackPressed()
        }

    }
}