package com.oldschoolbastard.budgettracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oldschoolbastard.budgettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var transactions : ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        transactions = arrayListOf(
            Transaction("Weekend budget", 1100.00),
            Transaction("Bananas", -30.00),
            Transaction("Apples", -80.00),
            Transaction("Breakfast", -150.00),
            Transaction("Juice", -30.00),
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerview.apply{
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
    }
}