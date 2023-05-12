package com.monsieur.cloy.merchshop.presentation.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.Order
import com.monsieur.cloy.domain.models.OrderItem
import com.monsieur.cloy.domain.models.OrderStatus
import com.monsieur.cloy.merchshop.R

class OrderRecyclerAdapter  (val context: Context): RecyclerView.Adapter<OrderRecyclerAdapter.ViewHolder>() {

    var orders: List<Order>? = null
    var orderItems: List<OrderItem>? = null

    private var onCancelOrderListener: ((order: Order) -> Unit)? = null

    fun setOnCancelOrderListener(l: (order: Order) -> Unit){
        onCancelOrderListener = l
    }



    @SuppressLint("NotifyDataSetChanged")
    fun setItems(orders : List<Order>, orderItems: List<OrderItem>){
        this.orders = orders
        this.orderItems = orderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.order_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(orders != null && orders!!.isNotEmpty()){
            val order = orders!![position]

            if(order.statusId == OrderStatus.Canceled.id || order.statusId == OrderStatus.Complete.id){
                holder.cancel.visibility = View.GONE
            }else{
                holder.cancel.visibility = View.VISIBLE
            }

            holder.status.text = OrderStatus.getStatusById(order.statusId).name

            holder.name.text = "Заказ от " + order.dateCreation.toLocalDate().toString()
            holder.sum.text = order.orderSum.toString()

            holder.cancel.setOnClickListener {
                onCancelOrderListener?.invoke(order)
            }

            val itemsRecycler = OrderItemRecyclerAdapter(context)

            holder.recycler.adapter = itemsRecycler
            itemsRecycler.setItems(orderItems!!.filter { it.orderId == order.id })

        }
    }

    override fun getItemCount(): Int {
        return if(orders != null){
            orders!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var status: TextView = itemView.findViewById(R.id.order_status)
        var sum: TextView = itemView.findViewById(R.id.order_sum)
        var name: TextView = itemView.findViewById(R.id.order_name)
        var cancel: Button = itemView.findViewById(R.id.delete_from_basket)
        var recycler: RecyclerView = itemView.findViewById(R.id.orders_items_recycler)
    }
}