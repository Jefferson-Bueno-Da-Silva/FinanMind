package com.example.finanmind.models

data class Transactions(
    var incomes: List<Transaction>,
    var incomesTotal: Double,
    var invoices: List<Transaction>,
    var invoicesTotal: Double,
)
