package com.monsieur.cloy.merchshop.presentation.profile

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.CurrencyTransactionType
import com.monsieur.cloy.merchshop.R

class CurrencyTransactionRecyclerAdapter  (val context: Context): RecyclerView.Adapter<CurrencyTransactionRecyclerAdapter.ViewHolder>() {

    var currencyTransactions: List<CurrencyTransaction>? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setItems(currencyTransactions : List<CurrencyTransaction>){
        this.currencyTransactions = currencyTransactions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.currency_transaction_card, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(currencyTransactions != null && currencyTransactions!!.isNotEmpty()){
            val currencyTransaction = currencyTransactions!![position]

            holder.ctName.text =
                CurrencyTransactionType.getTypeById(currencyTransaction.currencyTransactionTypeId).name
            holder.ctValue.text = currencyTransaction.points.toString()

        }
    }

    override fun getItemCount(): Int {
        return if(currencyTransactions != null){
            currencyTransactions!!.size
        } else{
            0
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ctName: TextView = itemView.findViewById(R.id.c_t_name)
        var ctValue: TextView = itemView.findViewById(R.id.c_t_value)
    }
}