package com.tsu.codewars2.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="data")
data class DataEntity (

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val input: String,
    val output:String

){
    fun toDomain() = Data(
        id = id,
        input = input,
        output=output

    )
}