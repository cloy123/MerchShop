package com.monsieur.cloy.merchshop.presentation.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.presentation.catalog.PageDecoder
import com.monsieur.cloy.merchshop.utilits.GlideOptions
import com.monsieur.cloy.merchshop.utilits.path

class OrderItemRecyclerAdapter  (val context: Context): RecyclerView.Adapter<OrderItemRecyclerAdapter.ViewHolder>() {

    var orderItems: List<OrderItem>? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(orderItems : List<OrderItem>){
        this.orderItems = orderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_item_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(orderItems != null && orderItems!!.isNotEmpty()){
            val orderItem = orderItems!![position]

            Glide.with(context)
                .load(path + orderItem.product.imageFileName)//http://192.168.0.105:5088
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                    }
                )
                .placeholder(R.drawable.test_image)
                .error(R.drawable.error)
                .apply(GlideOptions.option(PageDecoder.PAGE_DECODER, true))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                //.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                //.skipMemoryCache(true)
                .into(holder.image)

            holder.size.text = orderItem.product.sizeName
            holder.productName.text = orderItem.product.typeName
            holder.color.text = "Цвет: " + orderItem.product.colorName
            holder.price.text = orderItem.itemPrice.toString()

        }
    }

    override fun getItemCount(): Int {
        return if(orderItems != null){
            orderItems!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var size: TextView = itemView.findViewById(R.id.size)
        var price: TextView = itemView.findViewById(R.id.product_price)
        var color: TextView = itemView.findViewById(R.id.product_color)
    }
}