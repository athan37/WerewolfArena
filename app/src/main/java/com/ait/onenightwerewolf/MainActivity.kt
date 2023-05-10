package com.ait.onenightwerewolf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ait.onenightwerewolf.model.WerewolfViewModel
import com.ait.onenightwerewolf.ui.screens.OneNightWerewolf
import com.ait.onenightwerewolf.ui.theme.OneNightWerewolfTheme
import java.util.*

class WerewolfViewModelFactory(
    private val roomId: String
) : ViewModelProvider.Factory {

    private val playerId = UUID.randomUUID().toString()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WerewolfViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WerewolfViewModel(roomId, playerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainActivity : ComponentActivity() {
    private val viewModel: WerewolfViewModel by viewModels {
        // Replace "roomId" and "playerId" with actual values
        WerewolfViewModelFactory("roomId")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneNightWerewolfTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OneNightWerewolf(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OneNightWerewolfTheme {
        Greeting("Android")
    }
}