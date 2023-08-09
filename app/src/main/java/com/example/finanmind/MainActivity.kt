package com.example.finanmind

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.finanmind.ui.theme.FinanMindTheme
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finanmind.components.CardComponent
import com.example.finanmind.components.HeaderCardComponent
import com.example.finanmind.models.Transaction
import com.example.finanmind.models.Transactions
import com.example.finanmind.viewmodel.home.HomeViewModel
import java.util.Date


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinanMindTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val transactions by homeViewModel.uiState.collectAsState()
    val showOptions by remember {
        mutableStateOf(false)
    }


    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                ExtendedFloatingActionButton(
                    modifier = Modifier.height(50.dp),
                    text = { Text("Receita") },
                    icon = { Icon(Icons.Filled.AttachMoney, "Add incomes") },
                    onClick = {
                        homeViewModel.setIncomes(
                            Transaction(
                                value = 10.0,
                                title = "Renda",
                                dueDate = Date(),
                                paid = false
                            )
                        )
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                )
                ExtendedFloatingActionButton(
                    modifier = Modifier.height(50.dp),
                    text = { Text("Despesa") },
                    icon = { Icon(imageVector = Icons.Filled.MoneyOff, contentDescription = "Add invoice") },
                    onClick = {
                        homeViewModel.setInvoices(
                            Transaction(
                                value = 10.0,
                                title = "Débito",
                                dueDate = Date(),
                                paid = false
                            )
                        )
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                )
                FloatingActionButton(
                    modifier = Modifier,
                    onClick = {
                        homeViewModel.setInvoices(
                            Transaction(
                                value = 10.0,
                                title = "Débito",
                                dueDate = Date(),
                                paid = false
                            )
                        )
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add new transaction")
                }
            }
        },
    ) { innerPadding ->
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
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FinanMindTheme {
        HomeScreen()
    }
}

