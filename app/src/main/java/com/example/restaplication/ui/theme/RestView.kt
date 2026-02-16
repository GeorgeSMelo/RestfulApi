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
import org.json.JSONObject

@Composable
fun GetScreen(navController: NavController) {

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

        OutlinedTextField(user, {}, label = { Text("User") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(title, {}, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(body, {}, label = { Text("Body") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = {

                val url = "https://jsonplaceholder.typicode.com/posts/1"

                val request = StringRequest(
                    Request.Method.GET,
                    url,
                    { response ->
                        val json = JSONObject(response)
                        user = json.getString("userId")
                        title = json.getString("title")
                        body = json.getString("body")
                    },
                    {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                )

                Volley.newRequestQueue(context).add(request)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.get))
        }

        Button(
            onClick = { navController.navigate("post") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.ir_metodo_post))
        }
    }
}
