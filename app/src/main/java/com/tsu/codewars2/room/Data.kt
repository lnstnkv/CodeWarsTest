package com.tsu.codewars2.room

data class Data(
    val id: String,
    val input: String,
    val output:String,
) {
    fun toDomain() = DataEntity(
        id = id,
        input = input,
        output = output

    )
}