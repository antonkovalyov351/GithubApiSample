package com.example.githubapisample.data.datasource.repo

import com.example.githubapisample.data.mapper.RepoModelMapper
import com.example.githubapisample.data.net.GithubApiService
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single
import javax.inject.Inject

class RepoNetworkDataSource @Inject constructor(
    private val githubApiService: GithubApiService
) {

    fun searchRepositories(query: String, page: Int, pageSize: Int): Single<List<Repo>> =
        githubApiService.searchRepos(query, page, pageSize)
            .map(RepoModelMapper::searchRepoResponseToRepoList)
}
