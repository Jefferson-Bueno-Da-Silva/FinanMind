package com.example.finanmind.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun Double.toMoney(): String {
    return "${
        NumberFormat.getCurrencyInstance().format(this)
    }"
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}