package com.example.githubapisample.data.mapper

import com.example.githubapisample.data.db.entity.RepoEntity
import com.example.githubapisample.data.net.model.NetworkRepo
import com.example.githubapisample.data.net.model.RepoSearchResponse
import com.example.githubapisample.domain.vo.Repo

object RepoModelMapper {

    fun searchRepoResponseToRepoList(response: RepoSearchResponse) =
        response.items.map(this::networkRepoToRepo)

    fun networkRepoToRepo(networkRepo: NetworkRepo) =
        Repo(networkRepo.id, networkRepo.name, networkRepo.fullName)

    fun repoToEntity(repo: Repo) = RepoEntity(repo.id, repo.name, repo.fullName)

    fun entityToRepo(repo: RepoEntity) = Repo(repo.id, repo.name, repo.fullName)
}
