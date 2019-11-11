package com.example.githubapisample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubapisample.data.db.entity.RepoEntity
import com.example.githubapisample.data.db.entity.RepoSearchResultEntity

@Database(entities = [RepoEntity::class, RepoSearchResultEntity::class], version = 3)
abstract class GithubDb : RoomDatabase() {

    abstract fun repoDao(): RepoDao
}
