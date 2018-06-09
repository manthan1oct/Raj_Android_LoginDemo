package com.questionone.di.component

import com.questionone.di.module.DatabaseModule
import com.questionone.di.module.NetModule
import com.questionone.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = arrayOf(DatabaseModule::class, NetModule::class))
interface MyComponent {

    fun inject(activity: MainActivity)
}