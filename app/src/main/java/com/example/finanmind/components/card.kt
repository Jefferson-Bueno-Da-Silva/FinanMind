package com.example.finanmind.components

import android.transition.Fade
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finanmind.models.Transaction
import com.example.finanmind.ui.theme.SWhite
import com.example.finanmind.ui.theme.Separator
import com.example.finanmind.ui.theme.White
import com.example.finanmind.ui.theme.noRippleClickable
import com.example.finanmind.ui.theme.toMoney

@Composable
fun CardComponent(transaction: Transaction) {
    var cheked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(size = 8.dp)
            )
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Separator,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(30.dp)
                .height(30.dp)
                .background(
                    color = SWhite,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .noRippleClickable(onClick = {
                    cheked = !cheked
                }),
            contentAlignment = Alignment.Center
        )
        {
            this@Row.AnimatedVisibility(visible = cheked, enter = fadeIn(), exit = fadeOut()) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Checked item")
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = transaction.title,
            modifier = Modifier
                .weight(weight = 1F)
        )
        Text(text = transaction.value.toMoney())
    }
    Spacer(modifier = Modifier.height(8.dp))
}