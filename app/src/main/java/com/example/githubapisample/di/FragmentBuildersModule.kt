package com.example.githubapisample.di

import com.example.githubapisample.ui.main.RepoSearchFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): RepoSearchFragment
}
