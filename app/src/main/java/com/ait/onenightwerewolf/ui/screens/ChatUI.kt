import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatUI(modifier: Modifier = Modifier) {
    val messages = remember { mutableStateListOf<String>() }

    Column(modifier = modifier.fillMaxSize()) {
        MessagesList(messages, modifier = Modifier.weight(1f))
        MessageInput(onSendMessage = { message ->
            messages.add(message)
        })
    }
}

@Composable
fun MessageItem(message: String) {
    Text(message, modifier = Modifier.padding(8.dp))
}


@Composable
fun MessagesList(messages: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) { message ->
            MessageItem(message)
        }
    }
}

@Composable
fun MessageInput(onSendMessage: (String) -> Unit) {
    val messageInput = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        TextField(
            value = messageInput.value,
            onValueChange = { messageInput.value = it },
            placeholder = { Text("Type your message...") },
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = {
                onSendMessage(messageInput.value)
                messageInput.value = ""
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Send")
        }
    }
}