package com.example.githubapisample.data.repository

import android.annotation.SuppressLint
import com.example.githubapisample.data.datasource.repo.RepoLocalDataSource
import com.example.githubapisample.data.datasource.repo.RepoNetworkDataSource
import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val localDataSource: RepoLocalDataSource,
    private val networkDataSource: RepoNetworkDataSource
): RepoRepository {

    override fun searchRepositories(query: String): Flowable<List<Repo>> {
        return Flowable.concatArray(
            searchReposLocal(query),
            searchReposNetwork(query)
        ).subscribeOn(Schedulers.io())
    }

    private fun searchReposLocal(query: String): Flowable<List<Repo>> {
        return localDataSource.searchRepositories(query).toFlowable()
            .onErrorResumeNext(searchReposNetwork(query))
            .doOnNext {
                Timber.d("Dispatching ${it.size} repos from DB...")
            }
    }

    private fun searchReposNetwork(query: String): Flowable<List<Repo>> {
        return networkDataSource.searchRepositories(query).toFlowable()
            .doOnNext { repos ->
                Timber.d("Dispatching ${repos.size} repos from API...")
                storeSearch(query, repos)
            }
    }

    @SuppressLint("CheckResult")
    private fun storeSearch(query: String, repos: List<Repo>) {
        Flowable.fromCallable { localDataSource.saveSearch(query, repos) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${repos.size} users from API in DB...")
            }
    }
}
