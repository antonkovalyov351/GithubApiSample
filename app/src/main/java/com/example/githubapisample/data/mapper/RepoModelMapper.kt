package com.example.githubapisample.data.mapper

import com.example.githubapisample.data.net.model.RepoSearchResponse

object RepoModelMapper {
    fun searchRepoResponseToRepoList(response: RepoSearchResponse) = response.items
}
