package com.example.githubapisample.data.net.model

import com.example.githubapisample.domain.vo.Repo
import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
    @SerializedName("total_count")
    val total: Int = 0,
    val items: List<Repo>
)
