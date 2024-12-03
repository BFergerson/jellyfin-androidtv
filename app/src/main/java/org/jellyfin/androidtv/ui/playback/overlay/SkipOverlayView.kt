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

/**
 * A View that wraps the SkipOverlay composable and manages its visibility state.
 */
class SkipOverlayView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null
) : View(context, attrs) {

	private var visible: Boolean = true

	init {
		// Any initializations if necessary
	}

	/**
	 * Set visibility for the skip overlay.
	 * @param visible Boolean to control whether the skip overlay should be shown or hidden.
	 */
	fun setVisible(visible: Boolean) {
		this.visible = visible
		invalidate() // Trigger redraw when visibility changes
	}

	override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		// Setup or cleanup resources if needed
	}

	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		// Cleanup resources
	}

	override fun onDraw(canvas: android.graphics.Canvas?) {
		super.onDraw(canvas)
		// If you need to call Compose UI within a traditional Android view, you can use AndroidView
		// Here we render the composable with the current visibility state
	}

	/**
	 * Bind composable to the view. This would be called if integrating into a traditional Android view.
	 */
	@Composable
	fun renderSkipOverlayComposable() {
		SkipOverlayComposable(visible = visible)
	}
}
