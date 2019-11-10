package com.example.githubapisample.data.usecase

import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Flowable
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    fun searchRepositories(query: String): Flowable<List<Repo>> =
        repoRepository.searchRepositories(query)
}
