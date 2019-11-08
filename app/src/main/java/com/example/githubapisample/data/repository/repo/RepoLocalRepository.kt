package com.example.githubapisample.data.repository.repo

import android.util.SparseIntArray
import com.example.githubapisample.data.db.GithubDb
import com.example.githubapisample.data.db.entity.RepoSearchResultEntity
import com.example.githubapisample.data.mapper.RepoModelMapper
import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class RepoLocalRepository @Inject constructor(
    private val db: GithubDb
) : RepoRepository {

    private val repoDao = db.repoDao()

    override fun searchRepository(query: String): Single<List<Repo>> =
        repoDao.search(query).flatMap { result ->
            val order = SparseIntArray()
            result.repoIds.withIndex().forEach {
                order.put(it.value, it.index)
            }
            repoDao.loadById(result.repoIds).map { repos ->
                val mutable = repos.toMutableList()
                mutable.sortWith(Comparator { r1, r2 ->
                    val pos1 = order.get(r1.id)
                    val pos2 = order.get(r2.id)
                    pos1 - pos2
                })
                mutable
            }
        }.map { list -> list.map(RepoModelMapper::entityToRepo) }

    fun saveSearch(query: String, repos: List<Repo>) {

        val repoIds = repos.map { it.id }
        val repoSearchResult = RepoSearchResultEntity(
            query = query,
            repoIds = repoIds
        )
        db.runInTransaction {
            repoDao.insertRepos(repos.map(RepoModelMapper::repoToEntity))
            repoDao.insert(repoSearchResult)
        }
    }
}
