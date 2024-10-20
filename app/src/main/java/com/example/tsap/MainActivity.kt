package com.example.tsap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.tsap.ui.theme.TSAPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            TSAPTheme{
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainFunction(Modifier.padding(innerPadding))
                }
            }
        }
    }
}









