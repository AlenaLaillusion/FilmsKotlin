package com.example.fundamentalskotlin.data

import android.os.Parcel
import android.os.Parcelable


data class Movie(
    val image: String,
    val ageRating: String,
    val reviews: String,
    val name: String,
    val minut: String,
    val genre: String,
    val story: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(ageRating)
        parcel.writeString(reviews)
        parcel.writeString(name)
        parcel.writeString(minut)
        parcel.writeString(genre)
        parcel.writeString(story)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}

/*

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>
)

 */


