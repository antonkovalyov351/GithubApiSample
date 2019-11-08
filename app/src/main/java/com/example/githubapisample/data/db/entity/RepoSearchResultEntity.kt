package com.example.githubapisample.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.githubapisample.data.db.converter.GithubTypeConverters

@Entity
@TypeConverters(GithubTypeConverters::class)
data class RepoSearchResultEntity(
    @PrimaryKey
    val query: String,
    val repoIds: List<Int>
)
