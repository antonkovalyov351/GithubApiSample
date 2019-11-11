package com.example.githubapisample.data.net.model

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    val items: List<NetworkRepo>
)
