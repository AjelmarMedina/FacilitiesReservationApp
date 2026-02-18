package com.nu.dasmarinas.facilities.presentation.qr

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun QRCodeGeneratorPreview() {
    QRCodeGeneratorScreen()
}

@Composable
fun QRCodeGeneratorScreen() {
    var inputText by remember { mutableStateOf("") }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "QR Code Generator",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 32.dp, bottom = 8.dp)
        )

        Text(
            text = "Enter text or URL below",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Input Field
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                showError = false
            },
            label = { Text("Text or URL") },
            placeholder = { Text("e.g. https://example.com") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            isError = showError,
            supportingText = if (showError) {
                { Text("Please enter some text") }
            } else null,
            maxLines = 3
        )

        // Generate Button
        Button(
            onClick = {
                if (inputText.isBlank()) {
                    showError = true
                } else {
                    qrBitmap = generateQRCode(inputText, 512)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 24.dp)
        ) {
            Text("Generate QR Code", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        // QR Code Display
        qrBitmap?.let { bitmap ->
            Card(
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Generated QR Code",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            // Info Text
            Text(
                text = "QR Code generated successfully!",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// QR Code generation function
private fun generateQRCode(text: String, size: Int = 512): Bitmap {
    val hints = mapOf(EncodeHintType.MARGIN to 1)
    val bitMatrix = QRCodeWriter().encode(
        text,
        BarcodeFormat.QR_CODE,
        size,
        size,
        hints
    )

    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap.setPixel(
                x, y,
                if (bitMatrix[x, y]) android.graphics.Color.BLACK
                else android.graphics.Color.WHITE
            )
        }
    }
    return bitmap
}
