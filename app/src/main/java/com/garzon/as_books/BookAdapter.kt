package com.garzon.as_books

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView


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

        holder.id.text = book.id.toString()
        holder.title.text = book.title
        holder.bookMark.text = book.bookMark.toString()
        holder.author.text = book.author
        holder.pageNumber.text = book.pageNumber.toString()
        holder.type.text = book.type
        holder.isRead.text = book.isRead.toString()
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        var id = view.findViewById<TextView>(R.id.tvId)
        var title =view.findViewById<TextView>(R.id.tvTitle)
        var bookMark =view.findViewById<TextView>(R.id.tvBookMark)
        var author =view.findViewById<TextView>(R.id.tvAuthor)
        var pageNumber =view.findViewById<TextView>(R.id.tvPageNumber)
        var type =view.findViewById<TextView>(R.id.tvType)
        var isRead = view.findViewById<TextView>(R.id.tvIsRead)

        init {
            // Define click listener for the ViewHolder's View.
            id = view.findViewById(R.id.tvId)
            title = view.findViewById(R.id.tvTitle)
            bookMark = view.findViewById(R.id.tvBookMark)
            author = view.findViewById(R.id.tvAuthor)
            pageNumber = view.findViewById(R.id.tvPageNumber)
            type = view.findViewById(R.id.tvType)
            isRead = view.findViewById(R.id.tvIsRead)
        }
    }
}
