package com.monsieur.cloy.merchshop.utilits

import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.monsieur.cloy.merchshop.R
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.math.roundToInt

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.container,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment
            ).commit()
    }
    //APP_ACTIVITY.findViewById<ConstraintLayout>(R.id.container).visibility = View.VISIBLE
}

fun changeToolBar(menu: Boolean, homeButton: Boolean, title: String){
    if(toolbarMenu != null && toolbarMenu!!.children.count() > 0){
        for(menuItem in toolbarMenu?.children!!){
            menuItem.isVisible = menu
        }
    }
    if(homeButton){
        addHomeButton()
    }else{
        deleteHomeButton()
    }
    APP_ACTIVITY.supportActionBar?.title = title
}

private fun addHomeButton(){
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(true)
    actionBar?.setDisplayHomeAsUpEnabled(true)
}
private fun deleteHomeButton(){
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(false)
    actionBar?.setDisplayHomeAsUpEnabled(false)
}

fun backButton(){
    APP_ACTIVITY.onBackPressedDispatcher.onBackPressed()
    //APP_ACTIVITY.onBackPressed()
}

fun showToast(message: String){
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun calculatePrice(price: Int, discount: Int): Int{
    return (price.toFloat() - (discount.toFloat()/100 * price.toFloat())).roundToInt()
}

fun getUnsafeOkHttpClient(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}