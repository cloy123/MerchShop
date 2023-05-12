package com.monsieur.cloy.merchshop.presentation.catalog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.merchshop.R

class ProductTypesRecyclerAdapter : RecyclerView.Adapter<ProductTypesRecyclerAdapter.ViewHolder>() {

    private var types: ArrayList<ProductType>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTypes(types : ArrayList<ProductType>){
        this.types = types
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearChecked(){
        if(types != null){
            types!!.forEach { it.isChecked = false }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.filter_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(types != null){
            val type = types!![position]

            holder.text.text = type.name
            holder.checkBox.isChecked = type.isChecked

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                type.isChecked = isChecked
            }

            holder.card.setOnClickListener {
                holder.checkBox.isChecked = !holder.checkBox.isChecked
            }
            return
        }
    }

    fun getCheckedTypes(): ArrayList<ProductType>{
        return if(types == null){
            ArrayList<ProductType>()
        }else{
            types!!.filter { it.isChecked } as ArrayList<ProductType>
        }
    }

    override fun getItemCount(): Int {
        if(types != null){
            return types!!.size
        }
        return 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.text)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        var card: ConstraintLayout = itemView.findViewById(R.id.card)
    }
}