package com.garzon.as_books

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class BookAdapter (private val books: List<Book>):
    RecyclerView.Adapter<BookAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        Log.v("LOG", "onCreate")
        val v= LayoutInflater.from(parent.context).inflate(R.layout.book_item,parent,false)
        val vh = VH(v)
        vh.itemView.setOnClickListener{
            val book= books[vh.adapterPosition]
            val it = Intent(parent.context,UpdateActivity::class.java)
            it.putExtra("book", book)
            parent.context.startActivity(it)
        }
        return vh
    }
    override fun getItemCount(): Int {
        return books.size
    }
    override fun onBindViewHolder(holder: VH, position: Int) {
        Log.v("LOG", "ViewHolder")
        var book = books[position]

        holder.title.text = book.title
        holder.bookMark.text = book.bookMark
        holder.author.text = book.author
        holder.pageNumber.text = book.pageNumber
        holder.type.text = book.type
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        var title =view.findViewById<TextView>(R.id.title)
        var bookMark =view.findViewById<TextView>(R.id.bookMark)
        var author =view.findViewById<TextView>(R.id.author)
        var pageNumber =view.findViewById<TextView>(R.id.pageNumber)
        var type =view.findViewById<TextView>(R.id.type)

        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.title)
            bookMark = view.findViewById(R.id.bookMark)
            author = view.findViewById(R.id.author)
            pageNumber = view.findViewById(R.id.pageNumber)
            type = view.findViewById(R.id.type)
        }
    }
}