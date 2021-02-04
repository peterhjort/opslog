package com.example.counterops

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object OpsLogRepository {
    val log: LiveData<List<OpsLogEntry>> = OpsDatabase.getInstance().opsLogDAO.getAll()

    fun newOpsLogEntry(value: Int) {
        GlobalScope.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            OpsDatabase.getInstance().opsLogDAO.insert(
                OpsLogEntry(timestamp = System.currentTimeMillis(), amount = value, note = "")
            )
        }
    }
}