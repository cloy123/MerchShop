package com.monsieur.cloy.merchshop.presentation.events

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.Event
import com.monsieur.cloy.merchshop.R

class EventRecyclerAdapter  (val context: Context): RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder>() {

    private var events: List<Event>? = null

    private var onClickListener: ((event: Event) -> Unit)? = null

    fun setOnClickListener(l: (event: Event) -> Unit){
        onClickListener = l
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(events : List<Event>){
        this.events = events
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(events != null && events!!.isNotEmpty()){
            val event = events!![position]

            holder.eventName.text = event.name + if(event.isCompleted){
                " (Завершено)"
            }else{
                ""
            }

            holder.date.text = event.date.toLocalDate().toString()

            holder.card.setOnClickListener {
                onClickListener?.invoke(event)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(events != null){
            events!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var eventName: TextView = itemView.findViewById(R.id.event_name)
        var date: TextView = itemView.findViewById(R.id.date)
        var card: CardView = itemView.findViewById(R.id.card)
    }
}