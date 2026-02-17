package com.nu.dasmarinas.facilities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nu.dasmarinas.facilities.domain.model.UserRole
import com.nu.dasmarinas.facilities.presentation.navigation.FacilitiesApp
import com.nu.dasmarinas.facilities.ui.theme.FacilitiesReservationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            FacilitiesReservationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FacilitiesApp()
                }
            }
        }
    }
}
