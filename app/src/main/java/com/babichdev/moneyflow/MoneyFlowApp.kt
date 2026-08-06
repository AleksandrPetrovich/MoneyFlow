package com.babichdev.moneyflow

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.babichdev.moneyflow.R
import com.babichdev.moneyflow.di.AppContainer
import com.babichdev.moneyflow.presentation.app.AppViewModel
import com.babichdev.moneyflow.presentation.components.navigation.BottomNavigationBar
import com.babichdev.moneyflow.presentation.navigation.AppNavHost
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MoneyFlowApp(
    appContainer: AppContainer,
    appViewModel: AppViewModel
) {

    val navController = rememberNavController()

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        appViewModel.exportCsv.collectLatest { csv ->

            val fileName =
                "MoneyFlow_transactions_${
                    SimpleDateFormat(
                        "yyyy-MM-dd",
                        Locale.getDefault()
                    ).format(Date())
                }.csv"

            val file = File(
                context.cacheDir,
                fileName
            )

            file.writeText(csv)

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            val intent = Intent(Intent.ACTION_SEND).apply {

                type = "text/csv"

                putExtra(
                    Intent.EXTRA_STREAM,
                    uri
                )

                addFlags(
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
            }

            context.startActivity(
                Intent.createChooser(
                    intent,
                    context.getString(R.string.export_file_title)
                )
            )
        }
    }

    Scaffold(

        bottomBar = {
            BottomNavigationBar(navController)
        }

    ) { innerPadding ->

        AppNavHost(
            navController = navController,
            appContainer = appContainer,
            appViewModel = appViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}