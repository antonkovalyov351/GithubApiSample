package com.example.githubapisample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.githubapisample.ui.main.RepoSearchViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepoSearchViewModel::class)
    abstract fun bindMainViewModel(userViewModel: RepoSearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
