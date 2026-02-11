package com.example.restaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.restaplication.ui.theme.RestAplicationTheme
import com.example.restaplication.ui.theme.RestView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            RestAplicationTheme {
                Scaffold { innerPadding ->
                    RestView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


