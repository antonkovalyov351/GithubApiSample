package com.example.githubapisample.data.repository

import android.annotation.SuppressLint
import com.example.githubapisample.data.datasource.repo.RepoLocalDataSource
import com.example.githubapisample.data.datasource.repo.RepoNetworkDataSource
import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import com.example.githubapisample.domain.vo.Resource
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val localDataSource: RepoLocalDataSource,
    private val networkDataSource: RepoNetworkDataSource
) : RepoRepository {

    override fun searchRepositories(query: String): Flowable<Resource<List<Repo>>> {
        return Flowable.concatArray(
            sendLoading(),
            searchReposLocal(query),
            searchReposNetwork(query, DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        ).subscribeOn(Schedulers.io())
    }

    override fun searchRepositories(query: String, page: Int): Flowable<Resource<List<Repo>>> {
        return Flowable.concatArray(
            sendLoading(),
            searchReposLocal(query),
            searchReposNetwork(query, page, DEFAULT_PAGE_SIZE)
        ).subscribeOn(Schedulers.io())
    }

    private fun sendLoading() = Flowable.just(Resource.loading(emptyList<Repo>()))

    private fun searchReposLocal(query: String): Flowable<Resource<List<Repo>>> {
        return localDataSource.searchRepositories(query)
            .toFlowable()
            .map { Resource.success(it) }
            .onErrorResumeNext(sendLoading())
            .doOnNext { Timber.d("Dispatching ${it.data?.size} repos from DB...") }
    }

    private fun searchReposNetwork(
        query: String,
        page: Int,
        pageSize: Int
    ): Flowable<Resource<List<Repo>>> {
        return networkDataSource.searchRepositories(query, page, pageSize)
            .toFlowable()
            .doOnNext { repos ->
                Timber.d("Dispatching ${repos.size} repos from API...")
                storeSearch(query, repos)
            }.map { Resource.success(it) }
            .onErrorReturn { Resource.error(it.message!!, emptyList()) }
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

    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 30
    }
}
