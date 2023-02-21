package com.oldschoolbastard.budgettracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionListAdapter : RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder>() {
    private val transactions = listOf("Transaction 1", "Transaction 2", "Transaction 3", "Transaction 4", "Transaction 5")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount() = transactions.size

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val transactionName: TextView = itemView.transaction_name

        fun bind(transaction: String) {
            transactionName.text = transaction
        }
    }
}
