package com.monsieur.cloy.merchshop.presentation.events

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.merchshop.R

class EventResponsiblesRecyclerAdapter  (val context: Context): RecyclerView.Adapter<EventResponsiblesRecyclerAdapter.ViewHolder>() {

    private var responsibles: List<EventResponsible>? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(responsibles : List<EventResponsible>){
        this.responsibles = responsibles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_responsibles_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(responsibles != null && responsibles!!.isNotEmpty()){
            val responsible = responsibles!![position]

            holder.responsibleName.text = responsible.firstName + " " + responsible.lastName + " " + responsible.className
        }
    }

    override fun getItemCount(): Int {
        return if(responsibles != null){
            responsibles!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var responsibleName: TextView = itemView.findViewById(R.id.responsible_name)
    }
}