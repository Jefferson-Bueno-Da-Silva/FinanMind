package com.example.finanmind.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finanmind.ui.theme.toMoney

@Composable
fun HeaderCardComponent(label: String, value: Double){
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp)) {
        Text(text = label, modifier = Modifier.weight(1f))
        Text(text = value.toMoney())
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview
@Composable
fun HeaderCardPreview(){
    HeaderCardComponent("Entrada", 1600.00)
}