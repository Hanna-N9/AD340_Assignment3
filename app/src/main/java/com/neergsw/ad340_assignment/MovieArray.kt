package com.neergsw.recltest

import android.os.Parcel
import android.os.Parcelable

data class MovieArray(
    val imageSrc: Int,
    var title: String?,
    var year: String?,
    var director: String?,
    var source: String?,
    var description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageSrc)
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(director)
        parcel.writeString(source)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieArray> {
        override fun createFromParcel(parcel: Parcel): MovieArray {
            return MovieArray(parcel)
        }

        override fun newArray(size: Int): Array<MovieArray?> {
            return arrayOfNulls(size)
        }
    }
}