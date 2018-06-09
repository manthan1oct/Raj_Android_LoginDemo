package com.questionone.ui.main

import android.content.Context
import com.questionone.data.source.local.AppDatabase
import retrofit2.Retrofit

interface MainContract {

    interface Presenter {
        /**
         * This is the contact method for login
         */
        fun doLogin(userName: String, password: String, retrofit: Retrofit, db: AppDatabase)
    }

    interface View {
        fun showMessage(msg: String)
        fun getContext(): Context
        fun showProgress()
        fun hideProgressBar()
    }
}

