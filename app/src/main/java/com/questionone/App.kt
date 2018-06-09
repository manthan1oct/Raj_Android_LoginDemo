package com.questionone

import android.app.Application
import com.questionone.BuildConfig.BASE_URL
import com.questionone.di.component.DaggerMyComponent
import com.questionone.di.component.MyComponent
import com.questionone.di.module.DatabaseModule
import com.questionone.di.module.NetModule

class App : Application(){

    companion object {
        lateinit var myComponent: MyComponent
    }

    override fun onCreate() {
        super.onCreate()
        myComponent = DaggerMyComponent.builder()
                .netModule(NetModule(BASE_URL))
                .databaseModule(DatabaseModule(this))
                .build()
    }

    fun getComponent(): MyComponent {
        return myComponent
    }


}