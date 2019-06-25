package com.carter.androiddemo.di.module

import com.carter.androiddemo.MyApplication
import com.carter.androiddemo.core.data.DataManager
import com.carter.androiddemo.core.db.DBHelper
import com.carter.androiddemo.core.db.DBHelperImpl
import com.carter.androiddemo.core.http.HttpHelper
import com.carter.androiddemo.core.http.HttpHelperImpl
import com.carter.androiddemo.core.preference.PreferenceHelper
import com.carter.androiddemo.core.preference.PreferenceHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var application: MyApplication) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): MyApplication {
        return application
    }

    @Provides
    @Singleton
    internal fun provideHttpHelper(httpHelperImpl: HttpHelperImpl): HttpHelper {
        return httpHelperImpl
    }

    @Provides
    @Singleton
    internal fun provideDbHelper(dbHelperImpl: DBHelperImpl): DBHelper {
        return dbHelperImpl
    }

    @Provides
    @Singleton
    internal fun providePreferenceHelper(preferenceHelper: PreferenceHelperImpl): PreferenceHelper {
        return preferenceHelper
    }

    @Provides
    @Singleton
    internal fun provideDataManager(httpHelper: HttpHelper, dbHelper: DBHelper, preferenceHelper: PreferenceHelper): DataManager {
        return DataManager(httpHelper, dbHelper, preferenceHelper)
    }
}