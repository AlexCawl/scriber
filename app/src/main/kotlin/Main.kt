import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.alexcawl.scriber.component.dnd.DragAndDropField

@Composable
@Preview
fun App() {
    var fileName by remember { mutableStateOf("") }
    MaterialTheme {
        Column {
            DragAndDropField(
                infoLabel = "Drop file here",
                modifier = Modifier.height(100.dp).fillMaxWidth(),
                validate = {
                    println(it.name)
                    true
                },
                process = {
                    fileName = it.name
                }
            )
            Text(fileName)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
