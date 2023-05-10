package com.monsieur.cloy.merchshop.presentation.catalog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Option
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.merchshop.R
import com.monsieur.cloy.merchshop.utilits.GlideOptions
import com.monsieur.cloy.merchshop.utilits.calculatePrice
import com.monsieur.cloy.merchshop.utilits.path
import java.io.InputStream


class ProductRecyclerAdapter (val context: Context): RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder>() {

    private var products: List<List<Product>>? = null

    private var addToBasketListener: ((product: Product) -> Unit)? = null

    fun setAddToBasketListener(l: (product: Product) -> Unit){
        addToBasketListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(products : List<List<Product>>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(products != null && products!!.isNotEmpty()){
            val product = products!![position]

            Glide.with(context)
                .load(path+product[0].imageFileName)//http://192.168.0.105:5088
                .override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).listener(
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


            val sizes = ArrayList<String>()
            for (p in product){
                sizes.add(p.sizeName)
            }



            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                context,
                android.R.layout.simple_spinner_item, sizes
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            holder.sizes.adapter = adapter

            holder.sizes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val p = product.find { it.sizeName == sizes[holder.sizes.selectedItemId.toInt()] }
                    holder.productPrice.text = calculatePrice(p!!.price, p.discount).toString() + " ₽"
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            val p = product.find { it.sizeName == sizes[holder.sizes.selectedItemId.toInt()] }

            holder.productName.text = product[0].typeName + " Цвет: " + product[0].colorName

            holder.productPrice.text = calculatePrice(p!!.price, p.discount).toString() + " ₽"

            holder.addToBasket.setOnClickListener {
                //val p = product.find { it.sizeName == sizes[holder.sizes.selectedItemId.toInt()] }
                addToBasketListener?.invoke(p!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(products != null){
            products!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.product_image)
        var productName: TextView = itemView.findViewById(R.id.product_name)
        var productPrice: TextView = itemView.findViewById(R.id.product_price)
        var card: CardView = itemView.findViewById(R.id.card)
        var sizes: Spinner = itemView.findViewById(R.id.spinner_sizes)
        var addToBasket: Button = itemView.findViewById(R.id.add_to_basket)
    }
}

class PageDecoder(
    private val bitmapPool: BitmapPool
) : ResourceDecoder<InputStream, Bitmap> {

    companion object {
        val PAGE_DECODER: Option<Boolean> = Option.memory("abc")
    }

    override fun decode(source: InputStream, width: Int, height: Int, options: Options): Resource<Bitmap>? {
        return BitmapResource.obtain(BitmapFactory.decodeStream(source), bitmapPool)
    }

    override fun handles(source: InputStream, options: Options): Boolean = options.get(PAGE_DECODER) ?: false

}