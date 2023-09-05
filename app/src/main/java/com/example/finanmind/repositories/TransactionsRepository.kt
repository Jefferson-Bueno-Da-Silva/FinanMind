package com.example.finanmind.repositories

import androidx.compose.runtime.mutableStateOf
import com.example.finanmind.models.Transaction
import com.example.finanmind.models.Transactions

class TransactionsRepository {
    private val _invoices = mutableListOf<Transaction>()
    private val _incomes = mutableListOf<Transaction>()

    private val _transactions = mutableStateOf(
        Transactions(
            invoices = _invoices.toList(),
            invoicesTotal = _invoices.sumOf { it.value },
            incomes = _incomes.toList(),
            incomesTotal = _incomes.sumOf { it.value },
        )
    )
    val transactions get() = _transactions.value

    fun addIncomes(transaction: Transaction) {
        _incomes.add(transaction)
        updateState()
    }

    fun addInvoices(transaction: Transaction) {
        _invoices.add(transaction)
        updateState()
    }

    private fun updateState() {
        _transactions.value = transactions.copy(
            incomesTotal = _incomes.sumOf { it.value },
            invoicesTotal = _invoices.sumOf { it.value },
            invoices = _invoices,
            incomes = _incomes
        )
    }
}