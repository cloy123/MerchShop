package com.monsieur.cloy.merchshop.presentation.events

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.EventResponsible
import com.monsieur.cloy.domain.models.EventRole
import com.monsieur.cloy.merchshop.R

class EventRolesRecyclerAdapter  (val context: Context): RecyclerView.Adapter<EventRolesRecyclerAdapter.ViewHolder>() {

    private var roles: List<EventRole>? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(roles : List<EventRole>){
        this.roles = roles
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_role_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(roles != null && roles!!.isNotEmpty()){
            val role = roles!![position]

            holder.roleName.text = role.name
            holder.rolePrize.text = role.prize.toString()
        }
    }

    override fun getItemCount(): Int {
        return if(roles != null){
            roles!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roleName: TextView = itemView.findViewById(R.id.role_name)
        var rolePrize: TextView = itemView.findViewById(R.id.role_prize)
    }
}