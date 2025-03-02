package com.manoj.carrentalfetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.libraries.places.api.Places
import com.manoj.carrentalfetch.ui.theme.CarRentalFetchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "YOUR_API_KEY")
        }
        enableEdgeToEdge()
        setContent {
            CarRentalFetchTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) { padding ->
                    SearchInputBox(padding)
                }
            }
        }
    }
}
