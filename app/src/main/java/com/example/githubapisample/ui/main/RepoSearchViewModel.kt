package com.example.githubapisample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapisample.data.usecase.SearchRepoUseCase
import com.example.githubapisample.domain.vo.Repo
import com.example.githubapisample.domain.vo.Resource
import com.example.githubapisample.domain.vo.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class RepoSearchViewModel @Inject constructor(
    private val searchRepoUseCase: SearchRepoUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _searchResult = MutableLiveData<List<Repo>>(listOf())
    val searchResult: LiveData<List<Repo>> = _searchResult

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun search(query: String) {
        searchRepoUseCase.searchRepositories(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleSearchResult, Timber::e)
            .addTo(compositeDisposable)
    }

    fun loadMore() {

    }

    private fun handleSearchResult(result: Resource<List<Repo>>) {
        Timber.d("result = $result")
        when (result.status) {
            Status.LOADING -> _loading.value = true
            Status.SUCCESS -> {
                _loading.value = false
                _searchResult.value = result.data
            }
            Status.ERROR -> {
                _loading.value = false
                Timber.e(Exception(result.message))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
