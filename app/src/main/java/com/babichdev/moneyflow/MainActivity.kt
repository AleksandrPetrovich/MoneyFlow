package com.babichdev.moneyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.babichdev.moneyflow.ui.theme.MoneyFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyFlowTheme {
                MoneyFlowApp(
                    appContainer = (application as MoneyFlowApplication).container
                )
            }
        }
    }
}

/*@Preview
@Composable
fun MoneyFlowAppPreview(){
    MoneyFlowApp(
        appContainer = (application as MoneyFlowApplication).container
    )
}*/