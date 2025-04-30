package com.appambitdemo.appambitdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appambit.appambit.Test
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicAppambitDemo()
        }
    }
}

@Composable
fun BasicAppambitDemo() {
    var token by remember { mutableStateOf<String?>(null) }
    var response by remember { mutableStateOf("Waiting action..") }
    var loading by remember { mutableStateOf(false) }

    val test = remember { Test() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                loading = true
                response = "Creating consumer..."

                scope.launch {
                    test.storeConsumer { result ->
                        loading = false
                        if (result.isSuccess) {
                            token = result.getOrNull()
                            response = "Obtained Token: $token"
                        } else {
                            response = "Error: ${result.exceptionOrNull()?.message}"
                        }
                    }
                }
            },
            enabled = !loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Store Consumer")
        }

        Button(
            onClick = {
                if (token == null) {
                    response = "Need token to start session"
                    return@Button
                }

                loading = true
                response = "Login..."

                scope.launch {
                    test.startSession(
                        headers = mapOf("Authorization" to "Bearer $token")
                    ) { result ->
                        loading = false
                        response = if (result.isSuccess) {
                            "Session initialized: ${result.getOrNull()}"
                        } else {
                            "Error: ${result.exceptionOrNull()?.message}"
                        }
                    }
                }
            },
            enabled = !loading && token != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Session")
        }

        if (loading) {
            CircularProgressIndicator()
        }

        Text(
            text = response,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Token: ${token ?: "Not available"}",
            modifier = Modifier.fillMaxWidth()
        )
    }
}