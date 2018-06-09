package com.questionone.ui.main

import android.util.Log
import com.questionone.R
import com.questionone.data.source.local.AppDatabase
import com.questionone.data.source.local.Token
import com.questionone.data.source.remote.ApiService
import com.questionone.utils.IMEI
import com.questionone.utils.IMSI
import com.questionone.utils.isOnline
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    lateinit var apiService: ApiService

    /**
     * @userName : user name
     * @password : password of the account
     * This is the implementation login
     */
    override fun doLogin(userName: String, password: String, retrofit: Retrofit, db: AppDatabase) {

        if (userName.isBlank()) {
            view.showMessage(view.getContext().getString(R.string.str_err_msg_empty_user_name))
            return
        }
        if (password.isBlank()) {
            view.showMessage(view.getContext().getString(R.string.str_err_msg_empty_password))
            return
        }

        if(isOnline(view.getContext())) {

            view.showProgress()
            apiService = retrofit.create(ApiService::class.java)
            apiService.login(IMSI, IMEI, userName, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ data ->
                        data.response()?.headers()?.get("X-Acc")?.let {
                            db.getTokenDao().storeToken(Token(1, it))
                        }
                        view.hideProgressBar()
                        Log.d("doLogin", "RemoteDataSource >> login >> ${db.getTokenDao().getTokens().size}")
                        view.showMessage(data.response()?.body()?.errorMessage!!)

                    }, { e ->
                        view.hideProgressBar()
                        Log.e("doLogin", "RemoteDataSource >> login >> $e")
                        view.showMessage(view.getContext().getString(R.string.login_failed))
                    })
        }
        else
        {
            view.showMessage(view.getContext().getString(R.string.internet_connection_msg))
        }

    }
}