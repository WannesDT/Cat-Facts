package com.example.catfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.catfacts.ui.MainView
import com.example.catfacts.ui.theme.TestTheme
import com.example.catfacts.ui.util.TaskNavigationType

/**
 * The main activity for the Cat Facts application.
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)

                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            MainView(TaskNavigationType.BOTTOM_NAVIGATION)
                        }

                        else -> {
                            MainView(TaskNavigationType.NAVIGATION_RAIL)
                        }
                    }
                }
            }
        }
    }
}
