package com.example.core2extensionv2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class imgdata(
    val id: String,
    val name: String,
    val location: String,
    val rating: Float,
    val date: String
) : Parcelable {
}