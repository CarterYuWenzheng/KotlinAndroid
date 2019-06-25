package com.carter.androiddemo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.carter.androiddemo.core.data.DataManager
import com.carter.androiddemo.di.component.AppComponent
import com.carter.androiddemo.di.module.AppModule
import com.carter.androiddemo.di.module.HttpModule
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates

@SuppressLint("Registered")
class MyApplication : Application(), HasActivityInjector {

    lateinit var mAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject set

    companion object {

        val context: Context by Delegates.notNull()
        lateinit var refWatch: RefWatcher
        lateinit var mDataManager: DataManager
        @Inject set

        fun isNightMode(): Boolean {
            return MyApplication.mDataManager.getNightMode()
        }


        fun getRefWatcher(context: Context): RefWatcher {
            return this.refWatch
        }
    }

    override fun onCreate() {
        super.onCreate()
        refWatch = setupLeakCanary()
        if (mDataManager.getNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
//        DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .httpModule(HttpModule())
//                .build().inject(this)
    }

    private fun setupLeakCanary(): RefWatcher {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED
        } else {
            return LeakCanary.install(this)
        }
    }


    override fun activityInjector(): AndroidInjector<Activity>? {
        return mAndroidInjector
    }


}
