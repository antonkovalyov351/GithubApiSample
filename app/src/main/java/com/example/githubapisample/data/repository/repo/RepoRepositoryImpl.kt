package com.example.githubapisample.data.repository.repo

import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val localRepository: RepoLocalRepository,
    private val networkRepository: RepoNetworkRepository
) {

    fun searchRepositories(query: String): Single<List<Repo>> {
        return networkRepository.searchRepository(query)
    }
}