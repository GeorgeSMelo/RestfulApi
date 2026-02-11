package com.example.restaplication.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.restaplication.R

@Composable
fun RestView(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var user by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text(stringResource(R.string.userid)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(stringResource(R.string.title)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = body,
            onValueChange = { body = it },
            label = { Text(stringResource(R.string.body)) },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5
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
    }
}
