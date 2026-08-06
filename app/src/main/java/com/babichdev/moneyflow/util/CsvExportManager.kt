package com.babichdev.moneyflow.util

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import com.babichdev.moneyflow.presentation.model.TransactionUi
import java.io.File

class CsvExportManager(
    private val context: Context
) {

    fun export(
        transactions: List<TransactionUi>
    ) {

        val csv = CsvExporter.export(transactions)

        val file = File(
            context.cacheDir,
            "MoneyFlow_Transactions.csv"
        )

        file.writeText(csv)

        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val intent = Intent(Intent.ACTION_SEND).apply {

            type = "text/csv"

            putExtra(
                Intent.EXTRA_STREAM,
                uri
            )

            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(
            Intent.createChooser(
                intent,
                "Экспорт транзакций"
            )
        )
    }
}