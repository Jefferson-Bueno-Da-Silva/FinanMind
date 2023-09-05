package com.example.finanmind.viewmodel.home

import android.os.Debug
import androidx.lifecycle.ViewModel
import com.example.finanmind.models.Transaction
import com.example.finanmind.models.Transactions
import com.example.finanmind.repositories.TransactionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(
    private val transactionsRepository: TransactionsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun setIncomes(transaction: Transaction) {
        transactionsRepository.addIncomes(transaction)
        updateState()
    }

    fun setInvoices(transaction: Transaction) {
        transactionsRepository.addInvoices(transaction)
        updateState()
    }

    private fun updateState() {
        val transactions = transactionsRepository.transactions
        print(transactions)
        _uiState.value = uiState.value.copy(
            incomes = transactions.incomes,
            invoices = transactions.invoices,
            invoicesTotal = transactions.invoicesTotal,
            incomesTotal = transactions.incomesTotal
        )
    }

    data class UiState(
        val incomes: List<Transaction> = emptyList(),
        val invoices: List<Transaction> = emptyList(),
        val incomesTotal: Double = incomes.sumOf { it.value },
        val invoicesTotal: Double = invoices.sumOf { it.value }
    )
}