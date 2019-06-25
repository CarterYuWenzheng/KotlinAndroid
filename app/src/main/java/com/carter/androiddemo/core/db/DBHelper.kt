package com.carter.androiddemo.core.db

import com.carter.androiddemo.core.greenDao.HistoryData

interface DBHelper {

    fun addHistoryData(data: String): MutableList<HistoryData>

    fun clearAllHistoryData()

    fun deleteHistoryDataById(id: Long)

    fun loadAllHistoryData():MutableList<HistoryData>
}