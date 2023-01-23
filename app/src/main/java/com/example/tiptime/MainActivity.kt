package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

@Composable
fun TipTimeScreen() {

    var amountInput by remember {
        mutableStateOf("")
    }

    val amount = if (amountInput.toDoubleOrNull() != null) amountInput.toDouble() else 0.0

    var result by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))

        EditNumberField(value = amountInput, onValueChange = { amountInput = it })
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Button(
            onClick = { 
            result = calculateTip(amount)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Calculation")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Tip amount: $result",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun EditNumberField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "Bill amount",
                modifier = Modifier.fillMaxWidth()
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {

    val tip = (tipPercent / 100) * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}