package com.example.restaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.restaplication.ui.theme.RestAplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaplication.ui.theme.GetScreen
import com.example.restaplication.ui.theme.PostScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            RestAplicationTheme {

                val navController = rememberNavController()

                Scaffold { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "get",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("get") {
                            GetScreen(navController)
                        }

                        composable("post") {
                            PostScreen(navController)
                        }
                    }
                }
            }
        }
    }
}



