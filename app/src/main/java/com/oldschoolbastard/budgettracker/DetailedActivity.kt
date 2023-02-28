package com.oldschoolbastard.budgettracker

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.oldschoolbastard.budgettracker.databinding.ActivityAddTransactionBinding
//import kotlinx.android.synthetic.main.activity_add_transaction.*
//import kotlinx.android.synthetic.main.activity_detailed.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailedActivity : AppCompatActivity() {
    private lateinit var transaction : Transaction
    private lateinit var binding: ActivityAddTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transaction = intent.getSerializableExtra("transaction") as Transaction

        binding.labelInput.setText(transaction.label)
        binding.amountInput.setText(transaction.amount.toString())
        binding.descriptionInput.setText(transaction.description)

        binding.root.setOnClickListener {
            this.window.decorView.clearFocus()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
//
//        binding.rootView.setOnClickListener {
//            this.window.decorView.clearFocus()
//
//            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            imm.hideSoftInputFromWindow(it.windowToken, 0)
//        }

        binding.labelInput.addTextChangedListener {
            binding.addTransactionBtn.visibility = View.VISIBLE
            if(it!!.isNotEmpty())
                binding.labelLayout.error = null
        }

        binding.amountInput.addTextChangedListener {
            binding.addTransactionBtn.visibility = View.VISIBLE
            if(it!!.isNotEmpty())
                binding.amountLayout.error = null
        }

        binding.descriptionInput.addTextChangedListener {
            binding.addTransactionBtn.visibility = View.VISIBLE
        }

        binding.addTransactionBtn.setOnClickListener {
            val label = binding.labelInput.text.toString()
            val description = binding.descriptionInput.text.toString()
            val amount = binding.amountInput.text.toString().toDoubleOrNull()

            if(label.isEmpty())
                binding.labelLayout.error = "Please enter a valid label"

            else if(amount == null)
                binding.amountLayout.error = "Please enter a valid amount"
            else {
                val transaction  = Transaction(transaction.id, label, amount, description)
                update(transaction)
            }
        }

        binding.closeBtn.setOnClickListener {
            finish()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun update(transaction: Transaction){
        val db = Room.databaseBuilder(this,
            AppDatabase::class.java,
            "transactions").build()

        GlobalScope.launch {
            db.transactionDao().update(transaction)
            finish()
        }
    }
}