package com.example.githubapisample.domain.repository

import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Flowable

interface RepoRepository {

    fun searchRepositories(query: String): Flowable<List<Repo>>
}
