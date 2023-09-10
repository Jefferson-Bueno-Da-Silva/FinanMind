package com.example.finanmind.screens

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.finanmind.components.CardComponent
import com.example.finanmind.components.HeaderCardComponent
import com.example.finanmind.components.MainHeader
import com.example.finanmind.navigation.Screen
import com.example.finanmind.viewmodel.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
    val transactions by homeViewModel.uiState.collectAsState()
    var showOptions by rememberSaveable {
        mutableStateOf(false)
    }
    val density = LocalDensity.current

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            MainHeader(
                invoices = transactions.invoicesTotal,
                incomes = transactions.incomesTotal
            )
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                AnimatedVisibility(
                    visible = showOptions,
                    enter = slideInVertically {
                        with(density) { 120.dp.roundToPx() }
                    },
                    exit = slideOutVertically {
                        with(density) { 120.dp.roundToPx() }
                    }
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.height(50.dp),
                        text = { Text("Receita") },
                        icon = { Icon(Icons.Filled.AttachMoney, "Add incomes") },
                        onClick = {
//                            homeViewModel.setIncomes(
//                                Transaction(
//                                    value = 10.0,
//                                    title = "Renda",
//                                    dueDate = Date(),
//                                    paid = false
//                                )
//                            )
                        },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp)
                    )
                }
                AnimatedVisibility(
                    visible = showOptions,
                    enter = slideInVertically {
                        with(density) { 60.dp.roundToPx() }
                    },
                    exit = slideOutVertically {
                        with(density) { 60.dp.roundToPx() }
                    }
                ) {
                    ExtendedFloatingActionButton(
                        modifier = Modifier.height(50.dp),
                        text = { Text("Despesa") },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.MoneyOff,
                                contentDescription = "Add invoice"
                            )
                        },
                        onClick = {
//                            homeViewModel.setInvoices(
//                                Transaction(
//                                    value = 10.0,
//                                    title = "Débito",
//                                    dueDate = Date(),
//                                    paid = false
//                                )
//                            )
                        },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp)
                    )
                }
                FloatingActionButton(
                    modifier = Modifier,
                    onClick = {
                        navController.navigate(Screen.FormScreen.route)
//                        showOptions = !showOptions
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "add new transaction"
                    )
                }
            }
        },
    ) { innerPadding ->
        ListComponent(innerPadding, transactions)
    }
}

@Composable
private fun ListComponent(
    innerPadding: PaddingValues,
    transactions: HomeViewModel.UiState,
) {
    LazyColumn(
        modifier = Modifier.padding(innerPadding)
    ) {
        item { HeaderCardComponent("Receitas", transactions.incomesTotal) }
        items(
            items = transactions.incomes,
            itemContent = { item -> CardComponent(item) }
        )
        item { HeaderCardComponent("Despesas", transactions.invoicesTotal) }
        items(
            items = transactions.invoices,
            itemContent = { item -> CardComponent(item) }
        )
    }
}
