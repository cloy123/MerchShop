package com.monsieur.cloy.merchshop.presentation.basket

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.presentation.catalog.PageDecoder
import com.monsieur.cloy.merchshop.utilits.GlideOptions
import com.monsieur.cloy.merchshop.utilits.calculatePrice
import com.monsieur.cloy.merchshop.utilits.path
import com.monsieur.cloy.merchshop.utilits.showToast

class BasketRecyclerAdapter (val context: Context): RecyclerView.Adapter<BasketRecyclerAdapter.ViewHolder>() {

    private var basketItems: List<BasketItem>? = null

    private var deleteFromBasketListener: ((basketItem: BasketItem) -> Unit)? = null

    fun setDeleteFromBasketListener(l: (basketItem: BasketItem) -> Unit){
        deleteFromBasketListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(basketItems : List<BasketItem>){
        this.basketItems = basketItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.basket_item_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(basketItems != null && basketItems!!.isNotEmpty()){
            val basketItem = basketItems!![position]

            Glide.with(context)
                .load(path + basketItem.product.imageFileName)//http://192.168.0.105:5088
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

            if(basketItem.product.freeQuantity <= 0){
                holder.availability.visibility = View.GONE
            }else{
                holder.availability.visibility = View.VISIBLE
            }

            holder.size.text = basketItem.product.sizeName

            holder.productName.text = basketItem.product.typeName + " Цвет: " + basketItem.product.colorName

            holder.productPrice.text = calculatePrice(basketItem.product.price, basketItem.product.discount).toString()

            holder.deleteFromBasket.setOnClickListener {
                deleteFromBasketListener?.invoke(basketItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(basketItems != null){
            basketItems!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
        var availability: TextView = itemView.findViewById(R.id.product_availability)
        var card: CardView = itemView.findViewById(R.id.card)
        var size: TextView = itemView.findViewById(R.id.size)
        var deleteFromBasket: Button = itemView.findViewById(R.id.delete_from_basket)
    }
}