package com.carter.androiddemo.core.db

import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.core.constant.Constant
import com.carter.androiddemo.core.greenDao.DaoMaster
import com.carter.androiddemo.core.greenDao.DaoSession
import com.carter.androiddemo.core.greenDao.HistoryData
import com.carter.androiddemo.core.greenDao.HistoryDataDao
import javax.inject.Inject

class DBHelperImpl @Inject constructor() : DBHelper {

    val HISTORY_LIST_SIZE: Int = 10

    lateinit var daoSession: DaoSession
    lateinit var historyDataDao: HistoryDataDao
    lateinit var historyDataList: MutableList<HistoryData>
    lateinit var historyData: HistoryData
    lateinit var data: String

    init {
        initGreenDao()
    }

    private fun initGreenDao() {
        val daoOpenHelper = DaoMaster.DevOpenHelper(MyApplication.context, Constant.DB.DB_NAME)
        val datebase = daoOpenHelper.writableDatabase
        daoSession = DaoMaster(datebase).newSession()
        historyDataDao = daoSession.historyDataDao
    }

    override fun addHistoryData(data: String): MutableList<HistoryData> {
        this.data = data
        getHistoryDataList()
        createHistoryData()
        if (hisrotyDataForward()) {
            return historyDataList
        }
        if (historyDataList.size < HISTORY_LIST_SIZE) {
            historyDataDao.insert(historyData)
        } else {
            historyDataList.removeAt(0)
            historyDataList.add(historyData)
            historyDataDao.deleteAll()
            historyDataDao.insertInTx(historyDataList)
        }
        return historyDataList
    }

    private fun createHistoryData() {
        historyData = HistoryData()
        historyData.date = System.currentTimeMillis()
        historyData.data = data
    }

    private fun getHistoryDataList() {
        historyDataList = historyDataDao.loadAll()
    }

    /**
     * 历史数据前移
     *
     * @return 返回true表示查询的数据已存在，只需将其前移到第一项历史记录，否则需要增加新的历史记录
     */
    private fun hisrotyDataForward(): Boolean {
        //重复搜索时进行历史记录前移
        val iterator = historyDataList.iterator()
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            val historyData1 = iterator.next()
            if (historyData1.data.equals(data)) {
                historyDataList.remove(historyData1)
                historyDataList.add(historyData)
                historyDataDao.deleteAll()
                historyDataDao.insertInTx(historyDataList)
                return true
            }
        }
        return false
    }

    override fun clearAllHistoryData() {
        historyDataDao.deleteAll()
    }

    override fun deleteHistoryDataById(id: Long) {
        historyDataDao.deleteByKey(id)
    }

    override fun loadAllHistoryData(): MutableList<HistoryData> {
        return historyDataDao.loadAll()
    }

}