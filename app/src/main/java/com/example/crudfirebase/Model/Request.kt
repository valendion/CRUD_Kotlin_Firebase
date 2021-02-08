package com.example.crudfirebase.Model

import android.os.Parcel
import android.os.Parcelable

data class Request (
    var name: String,
    var email: String,
    var desk: String,
    var key: String?

) : Parcelable {

    constructor(): this("","","","")

//    var id: String = ""

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(desk)
        parcel.writeString(key)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Request> {
        override fun createFromParcel(parcel: Parcel): Request {
            return Request(parcel)
        }

        override fun newArray(size: Int): Array<Request?> {
            return arrayOfNulls(size)
        }
    }


}