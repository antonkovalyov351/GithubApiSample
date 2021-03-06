package com.example.githubapisample.di

import android.app.Application
import androidx.room.Room
import com.example.githubapisample.data.datasource.repo.RepoLocalDataSource
import com.example.githubapisample.data.datasource.repo.RepoNetworkDataSource
import com.example.githubapisample.data.db.GithubDb
import com.example.githubapisample.data.db.RepoDao
import com.example.githubapisample.data.net.GithubApiService
import com.example.githubapisample.data.repository.RepoRepositoryImpl
import com.example.githubapisample.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): GithubApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GithubApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): GithubDb {
        return Room.databaseBuilder(app, GithubDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: GithubDb): RepoDao {
        return db.repoDao()
    }

    @Singleton
    @Provides
    fun provideRepoRepository(
        repoLocalDataSource: RepoLocalDataSource,
        repoNetworkDataSource: RepoNetworkDataSource
    ): RepoRepository {
        return RepoRepositoryImpl(repoLocalDataSource, repoNetworkDataSource)
    }
}
