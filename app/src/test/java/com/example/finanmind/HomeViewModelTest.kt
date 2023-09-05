package com.example.finanmind

import com.example.finanmind.models.Transaction
import com.example.finanmind.repositories.TransactionsRepository
import com.example.finanmind.viewmodel.home.HomeViewModel
import org.junit.Assert.*
import org.junit.Test
import java.util.Date

class HomeViewModelTest {
    private var _homeViewModel: HomeViewModel

    init {
        val transactionsRepository = TransactionsRepository()
        _homeViewModel = HomeViewModel(transactionsRepository)
    }

    @Test
    fun should_be_add_income_transaction() {
        assertEquals(_homeViewModel.uiState.value.incomes, emptyList<Transaction>())

        val expectedValue =
            listOf(Transaction(value = 10.0, title = "Renda", dueDate = Date(), paid = false))

        _homeViewModel.setIncomes(
            expectedValue[0]
        )

        assertEquals(_homeViewModel.uiState.value.incomes, expectedValue)
        assertEquals(10.0, _homeViewModel.uiState.value.incomesTotal, 0.0)
    }

    @Test
    fun should_be_add_invoice_transaction() {
        assertEquals(_homeViewModel.uiState.value.invoices, emptyList<Transaction>())

        val expectedValue =
            listOf(Transaction(value = 10.0, title = "Debitos", dueDate = Date(), paid = false))

        _homeViewModel.setInvoices(
            Transaction(value = 10.0, title = "Debitos", dueDate = Date(), paid = false)
        )

        assertEquals(_homeViewModel.uiState.value.invoices, expectedValue)
        assertEquals(10.0, _homeViewModel.uiState.value.invoicesTotal, 0.0)
    }
}