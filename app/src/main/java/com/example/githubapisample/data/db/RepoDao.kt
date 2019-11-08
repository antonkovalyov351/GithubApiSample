package com.example.githubapisample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapisample.data.db.entity.RepoEntity
import com.example.githubapisample.data.db.entity.RepoSearchResultEntity
import io.reactivex.Single

@Dao
interface RepoDao {

    @Query("SELECT * FROM RepoSearchResultEntity WHERE `query` = :query")
    fun search(query: String): Single<RepoSearchResultEntity>

    @Query("SELECT * FROM RepoEntity WHERE id in (:repoIds)")
    fun loadById(repoIds: List<Int>): Single<List<RepoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repositories: List<RepoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: RepoSearchResultEntity)
}
