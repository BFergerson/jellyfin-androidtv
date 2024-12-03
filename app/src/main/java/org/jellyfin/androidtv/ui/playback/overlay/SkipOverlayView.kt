package org.jellyfin.androidtv.ui.playback.overlay

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Duration
import org.jellyfin.androidtv.R
import androidx.compose.ui.platform.LocalContext
import org.jellyfin.androidtv.ui.playback.segment.MediaSegmentRepository

/**
 * A composable for the Skip Overlay.
 * @param visible Flag to control whether the overlay is visible or not.
 */
@Composable
fun SkipOverlayComposable(
	visible: Boolean
) {
	// Use AnimatedVisibility for smooth visibility transitions
	AnimatedVisibility(
		visible = visible,
		enter = fadeIn(),
		exit = fadeOut(),
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp)
	) {
		// Display the overlay in the bottom-right corner
		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp),
			contentAlignment = Alignment.BottomEnd
		) {
			// Skip Button with position details
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Text(
					text = stringResource(id = R.string.skip_to_position, "00:05"), // Example static text, replace with dynamic duration
					color = Color.White,
					modifier = Modifier.padding(bottom = 8.dp)
				)
				Button(
					onClick = { /* Handle skip action */ },
					modifier = Modifier.padding(8.dp)
				) {
					Text(text = stringResource(id = R.string.skip))
				}
			}
		}
	}
}

class SkipOverlayView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null
) : View(context, attrs) {
	private val _currentPosition = MutableStateFlow(Duration.ZERO)
	private val _targetPosition = MutableStateFlow<Duration?>(null)
	private val _skipUiEnabled = MutableStateFlow(true)

	var currentPosition: Duration
		get() = _currentPosition.value
		set(value) {
			_currentPosition.value = value
		}

	var currentPositionMs: Long
		get() = _currentPosition.value.inWholeMilliseconds
		set(value) {
			_currentPosition.value = value.milliseconds
		}

	var targetPosition: Duration?
		get() = _targetPosition.value
		set(value) {
			_targetPosition.value = value
		}

	var targetPositionMs: Long?
		get() = _targetPosition.value?.inWholeMilliseconds
		set(value) {
			_targetPosition.value = value?.milliseconds
		}

	var skipUiEnabled: Boolean
		get() = _skipUiEnabled.value
		set(value) {
			_skipUiEnabled.value = value
		}

	val visible: Boolean
		get() {
			val enabled = _skipUiEnabled.value
			val targetPosition = _targetPosition.value
			val currentPosition = _currentPosition.value

			return enabled && targetPosition != null && currentPosition <= (targetPosition - MediaSegmentRepository.SkipMinDuration)
		}

	@Composable
	fun Content() {
		SkipOverlayComposable(visible = visible)
	}
}
