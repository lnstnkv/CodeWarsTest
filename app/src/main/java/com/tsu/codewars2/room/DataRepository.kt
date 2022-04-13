package com.tsu.codewars2.room

import android.content.Context
import android.content.Entity
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import java.util.*

class DataRepository(context: Context) {
    private val dataDao = Database.getInstance(context).getDataDao()

    fun getData(id: String): DataEntity {
        return dataDao.getData(id)
    }
    suspend fun addNew(id: String, input: String, output: String) {
        dataDao.addData(
            DataEntity(
                id = id,
                input = input,
                output=output,
            )
        )
    }

    suspend fun updateData(data:DataEntity){
        return dataDao.updateData(data.id,data.input,data.output)
    }
}