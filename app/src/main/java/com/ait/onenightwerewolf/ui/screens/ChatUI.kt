import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ait.onenightwerewolf.model.WerewolfViewModel

@Composable
fun ChatUI(viewModel: WerewolfViewModel, modifier: Modifier = Modifier) {
    var messages by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    LaunchedEffect(Unit) {
        viewModel.listenToMessages { playerName, newMessage ->
            messages = messages + Pair(playerName, newMessage)
        }
    }

    Column(modifier = modifier) {
        MessagesList(messages, modifier = Modifier.weight(1f))
        MessageInput { message ->
            viewModel.sendMessage(message)
        }
    }
}
@Composable
fun MessageItem(playerName: String, message: String) {
    Text(text = "$playerName: $message")
}


@Composable
fun MessagesList(messages: List<Pair<String, String>>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) { (playerName, message) ->
            MessageItem(playerName, message)
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