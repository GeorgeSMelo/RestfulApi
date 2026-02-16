package com.example.restaplication.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restaplication.R

@Composable
fun PostScreen(navController: NavController) {

    val context = LocalContext.current

    var user by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("User") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text("Body") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {

                val url = "https://jsonplaceholder.typicode.com/posts"

                val request = object : StringRequest(
                    Request.Method.POST,
                    url,
                    { response ->
                        Toast.makeText(
                            context,
                            "RESULTADO POST = $response",
                            Toast.LENGTH_LONG
                        ).show()
                    },
                    { error ->
                        Toast.makeText(
                            context,
                            "Error: ${error.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                ) {
                    override fun getParams(): MutableMap<String, String> {
                        return hashMapOf(
                            "title" to title,
                            "body" to body,
                            "userId" to user
                        )
                    }
                }

                Volley.newRequestQueue(context).add(request)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.enviar))
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir a Método GET")
        }
    }
}
