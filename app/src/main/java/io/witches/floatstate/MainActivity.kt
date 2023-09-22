package io.witches.floatstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.witches.floatstate.ui.theme.FloatstateTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FloatstateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun longs(): Flow<Long> = flow {
    while (true) {
        val i = Instant.now()
        delay(1000)
        emit(i.toEpochMilli())
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val v by longs().collectAsState(initial = Instant.now().toEpochMilli())

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "Long works: ${v % 30000L}"
        )
        Text(
            text = "Double works: ${v % 30000.0}"
        )
        Text(
            text = "Float does not: ${v % 30000f}"
        )
        Text(
            text = "Even if cast: ${(v % 30000f).toLong()}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FloatstateTheme {
        Greeting("Android")
    }
}