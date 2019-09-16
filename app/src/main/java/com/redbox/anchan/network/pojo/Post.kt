package com.redbox.anchan.network.pojo

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("no")
    val number: Int,
    @SerializedName("now")
    val date: String,
    @SerializedName("com")
    val comment: String?,
    val tim: Long,
    val replies: Int?,
    val images: Int?,
    val sub: String?,
    val filename: String?,
    val ext: String?
)

