package com.garzon.as_books

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi

data class Book(var id: Int?, var pageNumber: Int, var bookMark: Int, var type: String, var author: String, var title: String, var isRead: Boolean): Parcelable {
   @RequiresApi(Build.VERSION_CODES.Q)
   constructor(parcel: Parcel) : this(
       parcel.readValue(Int::class.java.classLoader) as? Int?,
       parcel.readInt(),
       parcel.readInt(),
       parcel.readString().toString(),
       parcel.readString().toString(),
       parcel.readString().toString(),
       parcel.readBoolean()
   )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(pageNumber)
        parcel.writeInt(bookMark)
        parcel.writeString(type)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeByte(if (isRead) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}

