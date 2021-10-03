package com.example.catsimage.data.remote.retrofit.responce

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatsPhoto(
    val id: String,
    val url: String,
    val name: String
): Parcelable