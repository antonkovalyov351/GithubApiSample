package com.example.githubapisample.data.repository.repo

import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single
import javax.inject.Inject

class RepoLocalRepository @Inject constructor() : RepoRepository {

    // TODO: Add database
    override fun searchRepository(query: String): Single<List<Repo>> =
        Single.just(emptyList())
}
