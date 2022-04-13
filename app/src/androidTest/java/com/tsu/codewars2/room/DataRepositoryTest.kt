package com.tsu.codewars2.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertArrayEquals

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.security.AccessController.getContext

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class DataRepositoryTest : TestCase() {

    private lateinit var localRepository: DataRepository
    private lateinit var database: AppDatabase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        localRepository =
            DataRepository(
                context = getApplicationContext()
            )
    }

    @After
    fun cleanUp() {
        database.close()
    }

    @Test
    fun test_updateData_retrievesData() = runBlocking{
        val newData = Data("25", "0 1 2 4 5", "12 12 11 9 5 0")
        localRepository.addNew(newData.id,newData.input,newData.output)

        val updateData = Data("25", "0 1 2 5 5", "12 12 11 9 5 0")
        localRepository.updateData(updateData.toDomain())

        val result = localRepository.getData(updateData.id).toDomain()
        assertThat(result.input, `is`("0 1 2 5 5"))
        assertThat(result.output, `is`("12 12 11 9 5 0"))
        assertThat(result.id, `is`("25"))

    }

    @Test
    fun test_saveData_retrievesData()= runBlocking {
        val newData = Data("25", "0 1 2 4 5", "12 12 11 9 5 0")
        localRepository.addNew(newData.id,newData.input,newData.output)

        val result = localRepository.getData(newData.id).toDomain()

        assertThat(result.input, `is`("0 1 2 4 5"))
        assertThat(result.output, `is`("12 12 11 9 5 0"))
        assertThat(result.id, `is`("25"))

    }
}