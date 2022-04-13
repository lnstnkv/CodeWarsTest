package com.tsu.codewars2.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(dataDataEntity: DataEntity)

    @Query("UPDATE data SET input= :input, output= :output WHERE id= :id")
    suspend fun updateData(id: String, input: String,output:String)

    @Delete
    suspend fun deleteData(dataDataEntity: DataEntity)

    @Query("SELECT*FROM data WHERE id= :id")
    fun getData(id: String): DataEntity

}