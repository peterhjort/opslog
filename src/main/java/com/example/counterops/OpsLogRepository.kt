package com.example.counterops

import androidx.lifecycle.LiveData

object OpsLogRepository {
    val dao = OpsDatabase.getInstance().opsLogDAO
    val logData: LiveData<List<OpsLogEntry>> = dao.getAll()

    suspend fun newOpsLogEntry(amount: Int, noteText: String) {
        dao.insert(
            OpsLogEntry(timestamp = System.currentTimeMillis(), amount = amount, note = noteText)
        )
    }
}