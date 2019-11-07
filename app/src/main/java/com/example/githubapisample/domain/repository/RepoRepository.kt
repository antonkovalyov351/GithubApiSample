package com.example.githubapisample.domain.repository

import com.example.githubapisample.domain.vo.Repo
import io.reactivex.Single

interface RepoRepository {

    fun searchRepository(query: String): Single<List<Repo>>
}