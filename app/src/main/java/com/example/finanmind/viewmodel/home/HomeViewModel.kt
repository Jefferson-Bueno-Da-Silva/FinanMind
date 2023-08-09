package com.example.finanmind.viewmodel.home

import androidx.lifecycle.ViewModel
import com.example.finanmind.models.Transaction
import com.example.finanmind.models.Transactions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date


class HomeViewModel : ViewModel() {
    private val _incomes = MutableStateFlow<List<Transaction>>(listOf())
    private val _invoices = MutableStateFlow<List<Transaction>>(listOf())

    private val _uiState =
        MutableStateFlow(
            Transactions(
                incomes = listOf(),
                incomesTotal = 0.0,
                invoices = listOf(),
                invoicesTotal = 0.0
            )
        )
    val uiState: StateFlow<Transactions> = _uiState.asStateFlow()


    fun setIncomes(transaction: Transaction) {
        val incomes = _incomes.value.toMutableList()
        incomes.add(transaction)
        _incomes.value = incomes
        setTransaction()
    }

    fun setInvoices(transaction: Transaction) {
        val invoices = _invoices.value.toMutableList()
        invoices.add(transaction)
        _invoices.value = invoices
        setTransaction()
    }

    private fun setTransaction() {
        val incomes = _incomes.value.toMutableList()
        val invoices = _invoices.value.toMutableList()

        _uiState.value = Transactions(
            incomes,
            incomesTotal = incomes.sumOf { it.value },
            invoices,
            invoicesTotal = invoices.sumOf { it.value }
        )
    }
}