package com.example.githubapisample.data.usecase

import android.annotation.SuppressLint
import com.example.githubapisample.data.repository.repo.RepoLocalRepository
import com.example.githubapisample.data.repository.repo.RepoNetworkRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(
    private val localRepository: RepoLocalRepository,
    private val networkRepository: RepoNetworkRepository
) {

    fun searchRepositories(query: String): Flowable<List<Repo>> {
        return Flowable.concatArray(
            searchReposLocal(query),
            searchReposNetwork(query)
        ).subscribeOn(Schedulers.io())
    }

    private fun searchReposLocal(query: String): Flowable<List<Repo>> {
        return localRepository.searchRepository(query).toFlowable()
            .onErrorResumeNext(searchReposNetwork(query))
            .doOnNext {
                Timber.d("Dispatching ${it.size} repos from DB...")
            }
    }

    private fun searchReposNetwork(query: String): Flowable<List<Repo>> {
        return networkRepository.searchRepository(query).toFlowable()
            .doOnNext { repos ->
                Timber.d("Dispatching ${repos.size} repos from API...")
                storeSearch(query, repos)
            }
    }

    @SuppressLint("CheckResult")
    private fun storeSearch(query: String, repos: List<Repo>) {
        Flowable.fromCallable { localRepository.saveSearch(query, repos) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Timber.d("Inserted ${repos.size} users from API in DB...")
            }
    }
}
