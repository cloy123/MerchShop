package com.monsieur.cloy.merchshop.presentation.events

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.EventParticipant
import com.monsieur.cloy.merchshop.R

class FinishParticipantRecyclerAdapter  (val context: Context): RecyclerView.Adapter<FinishParticipantRecyclerAdapter.ViewHolder>() {

    var participants: List<EventParticipant>? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(participants : List<EventParticipant>){
        this.participants = participants
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.finish_participant_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(participants != null && participants!!.isNotEmpty()){
            val participant = participants!![position]

            holder.participantName.text = participant.firstName + " " + participant.lastName + " " + participant.className
            holder.participantRole.text = participant.role.name

            holder.isVisit.setOnCheckedChangeListener { buttonView, isChecked ->
                participant.isVisit = !participant.isVisit
            }
        }
    }

    override fun getItemCount(): Int {
        return if(participants != null){
            participants!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var participantName: TextView = itemView.findViewById(R.id.participant_name)
        var participantRole: TextView = itemView.findViewById(R.id.participant_role)
        var isVisit: CheckBox = itemView.findViewById(R.id.is_visit)
    }
}