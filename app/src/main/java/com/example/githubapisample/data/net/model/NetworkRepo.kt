package com.example.githubapisample.data.net.model

import com.google.gson.annotations.SerializedName

data class NetworkRepo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String
)
