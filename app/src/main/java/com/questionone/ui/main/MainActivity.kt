package com.questionone.ui.main

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.questionone.App
import com.questionone.R
import com.questionone.data.source.local.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject lateinit var retrofit: Retrofit
    @Inject lateinit var db: AppDatabase

    override fun getContext(): Context {
        return this
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private var mainPresenter: MainPresenter = MainPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).getComponent().inject(this)
        setListener()
    }

    /**
     * This method is to set up all the listeners for the specific view.
     */
    private fun setListener() {
        btnLogin.setOnClickListener {
            mainPresenter.doLogin(tvUserName.text.toString(), tvPassword.text.toString(), retrofit, db)
        }
    }

    private var progressBar: ProgressDialog? = null

    /**
     * Show progress bar when we do network call.
     */
    override fun showProgress() {
        hideProgressBar()
        progressBar = ProgressDialog(this)
        progressBar!!.setCancelable(true)
        progressBar!!.setMessage(getString(R.string.loading))
        progressBar!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar!!.show()
    }

    /**
     * Hide progress bar after network
     */
    override fun hideProgressBar() {
        if (progressBar != null && progressBar!!.isShowing) {
            progressBar!!.hide()
        }
    }
}
