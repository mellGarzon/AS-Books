package com.garzon.as_books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val deleteButton = findViewById<FloatingActionButton>(R.id.floatingActionDeleteButton)
        val button = findViewById<Button>(R.id.button)
        val title = findViewById<EditText>(R.id.title)
        val pageNumber = findViewById<EditText>(R.id.pageNumber)
        val author = findViewById<EditText>(R.id.author)
        val bookMark = findViewById<EditText>(R.id.bookMark)
        val type = findViewById<EditText>(R.id.type)

        val book = intent.getParcelableExtra<Book>("book")

        title.setText(book?.title.toString())
        pageNumber.setText(book?.pageNumber.toString())
        author.setText(book?.author.toString())
        bookMark.setText(book?.bookMark.toString())
        type.setText(book?.type.toString())

        button.setOnClickListener{
            val bookUpdate = Book(book?.id, pageNumber.toString().toInt(), bookMark.toString().toInt(), type.toString(), author.toString(), title.toString(), false)
            val bookDao = BookDao(this)
            bookDao.update(bookUpdate)
            onBackPressed()
        }

        deleteButton.setOnClickListener {
            val bookDao = BookDao(this)
            if (book != null) {
                bookDao.remove(book)
            }
            onBackPressed()
        }

    }
}