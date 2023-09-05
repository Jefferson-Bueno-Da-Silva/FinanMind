package com.example.finanmind.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finanmind.ui.theme.Green
import com.example.finanmind.ui.theme.Red
import com.example.finanmind.ui.theme.toMoney

@Composable
fun MainHeader(invoices: Double, incomes: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Débitos")
            Text(
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = Red,
                text = invoices.toMoney()
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Saldo")
            Text(
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge,
                color = if (incomes >= invoices) Green else Red,
                text = (incomes - invoices).toMoney()
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Renda")
            Text(
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = Green,
                text = incomes.toMoney()
            )
        }
    }
}

@Preview
@Composable
fun MainHeaderPreview() {
    MainHeader(400.5, 600.0)
}