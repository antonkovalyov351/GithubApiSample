package com.example.githubapisample.domain.repository

import com.example.githubapisample.domain.vo.Repo
import com.example.githubapisample.domain.vo.Resource
import io.reactivex.Flowable

interface RepoRepository {

    fun searchRepositories(query: String): Flowable<Resource<List<Repo>>>

    fun searchRepositories(query: String, page: Int): Flowable<Resource<List<Repo>>>
}
