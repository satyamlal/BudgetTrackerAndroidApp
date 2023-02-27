package com.oldschoolbastard.budgettracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.oldschoolbastard.budgettracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var transactions: ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        transactions = arrayListOf(
            Transaction("Weekend budget", 1100.00),
            Transaction("Bananas", -30.00),
            Transaction("Apples", -80.00),
            Transaction("Breakfast", -150.00),
            Transaction("Juice", -30.00),
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerview.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
        updateDashboard()
    }

    private fun updateDashboard() {

        val totalAmount = transactions.map{ it.amount }.sum()
        val budgetAmount = transactions.filter{ it.amount > 0 }.map{ it.amount }.sum()
        val expenseAmount = totalAmount - budgetAmount

        binding.totalBalance.text = "₹%.2f".format(totalAmount)
        binding.budgetLeft!!.text = "₹%.2f".format(budgetAmount)
        binding.totalExpense!!.text = "₹%.2f".format(expenseAmount)
    }
}