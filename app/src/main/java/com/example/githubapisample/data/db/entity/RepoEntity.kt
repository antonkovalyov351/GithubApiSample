package com.example.githubapisample.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepoEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    val name: String
)
