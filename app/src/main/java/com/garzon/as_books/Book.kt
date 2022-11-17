package com.garzon.as_books

import android.os.Parcel
import android.os.Parcelable

data class Book(var id: Int?, var pageNumber: Int, var bookMark: Int, var type: String, var author: String, var title: String, var isRead: Boolean): Parcelable {
   constructor(parcel: Parcel) : this(
       parcel.readValue(Int::class.java.classLoader) as? Int
   )
}

