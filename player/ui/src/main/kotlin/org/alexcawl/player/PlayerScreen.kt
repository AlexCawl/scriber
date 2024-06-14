package org.alexcawl.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Restore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.alexcawl.scriber.mvi.compose.StoreScope

@Composable
fun PlayerScreen(modifier: Modifier) = StoreScope<PlayerScreenState, PlayerScreenAction, PlayerScreenStore> {
    val state: PlayerScreenState by this.state.collectAsState()
    PlayerScreenContent(
        state = state,
        event = this::consume,
        modifier = modifier
    )
}

@Composable
private fun PlayerScreenContent(
    state: PlayerScreenState,
    event: (PlayerScreenAction) -> Unit,
    modifier: Modifier = Modifier
) = Column(modifier) {
    when (val image = state.image) {
        null -> Unit
        else -> Image(bitmap = image, contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { event(PlayerScreenAction.Restart) } ) {
            Icon(
                imageVector = Icons.Rounded.Restore,
                contentDescription = null
            )
        }
    }
}