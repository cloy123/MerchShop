package com.monsieur.cloy.merchshop.utilits

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.module.LibraryGlideModule
import com.monsieur.cloy.merchshop.presentation.catalog.PageDecoder
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

@GlideModule
class UnsafeOkHttpGlideModule: AppGlideModule () {
    override fun registerComponents(@NonNull context: Context, @NonNull glide: Glide, @NonNull registry: Registry) {
        val client = getUnsafeOkHttpClient()
        glide.registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
        registry.prepend(InputStream::class.java, Bitmap::class.java, PageDecoder(glide.bitmapPool))
    }
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        val client = OkHttpClient.Builder()
//            .readTimeout(15, TimeUnit.SECONDS)
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .build()
//        val factory = OkHttpUrlLoader.Factory(client)
//        glide.registry.replace(
//            GlideUrl::class.java,
//            InputStream::class.java, factory
//        )
//    }
}