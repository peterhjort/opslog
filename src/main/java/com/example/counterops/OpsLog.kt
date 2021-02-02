package com.example.counterops

import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class OpsLogEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val amount: Int
    )

interface OpsLogDAO {
    @Insert
    fun insert(entry: OpsLogEntry)
    @Query("select * from OpsLogEntry where id == :key")
    fun get(key: Int)
    @Query("select * from OpsLogEntry")
    fun getAll(): MutableLiveData<List<OpsLogEntry>>
    @Query("select sum(amount) from OpsLogEntry")
    fun getSum(): MutableLiveData<Int>
}