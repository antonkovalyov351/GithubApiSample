package com.example.githubapisample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisample.data.usecase.SearchRepoUseCase
import com.example.githubapisample.domain.vo.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
    private val searchRepoUseCase: SearchRepoUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _repoList = MutableLiveData<List<Repo>>(listOf())
    val repoList: LiveData<List<Repo>> = _repoList

    fun search(query: String) {
        compositeDisposable.add(
            searchRepoUseCase.searchRepositories(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _repoList.postValue(it) },
                    { Timber.e(it) }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
