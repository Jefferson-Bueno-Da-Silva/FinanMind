package com.example.finanmind.models

import java.util.Date

data class Transaction(
    var value: Double,
    var title: String,
    var dueDate: Date,
    var paid: Boolean,
)
