package com.example.githubapisample.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.githubapisample.data.db.entity.RepoEntity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepoDaoTest : GithubDbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertSearchResult() {
        val repo = RepoEntity(555, "Test repo")
        db.repoDao().insert(repo)
        val repoFromDb = db.repoDao().loadById(555).blockingGet()
        assertThat(repoFromDb, notNullValue())
        assertThat(repoFromDb.name, `is`("Test repo"))
    }
}
