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

class ProductColorsRecyclerAdapter : RecyclerView.Adapter<ProductColorsRecyclerAdapter.ViewHolder>() {

    private var colors: ArrayList<Color>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setColors(colors : ArrayList<Color>){
        this.colors = colors
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearChecked(){
        if(colors != null){
            colors!!.forEach { it.isChecked = false }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.filter_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(colors != null){
            val color = colors!![position]

            holder.text.text = color.colorName
            holder.checkBox.isChecked = color.isChecked

            holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
                color.isChecked = isChecked
            }
            holder.card.setOnClickListener {
                holder.checkBox.isChecked = !holder.checkBox.isChecked
            }
            return
        }
    }

    fun getCheckedColors(): ArrayList<Color>{
        return if(colors == null){
            ArrayList<Color>()
        }else{
            colors!!.filter { it.isChecked } as ArrayList<Color>
        }
    }

    override fun getItemCount(): Int {
        if(colors != null){
            return colors!!.size
        }else{
            return 0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.text)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        var card: ConstraintLayout = itemView.findViewById(R.id.card)
    }
}