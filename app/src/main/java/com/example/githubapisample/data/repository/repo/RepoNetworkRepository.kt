package com.example.githubapisample.data.repository.repo

import com.example.githubapisample.data.mapper.RepoModelMapper
import com.example.githubapisample.data.net.GithubApiService
import com.example.githubapisample.domain.repository.RepoRepository
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single
import javax.inject.Inject

class RepoNetworkRepository @Inject constructor(
    private val githubApiService: GithubApiService
) : RepoRepository {

    override fun searchRepository(query: String): Single<List<Repo>> =
        githubApiService.searchRepos(query)
            .map(RepoModelMapper::searchRepoResponseToRepoList)
}
