package com.devikiran.assignments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.devikiran.assignments.performance.LazyListPerformance
import com.devikiran.assignments.ui.theme.AssignmentsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentsTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            testTagsAsResourceId = true
                        },
                ) { innerPadding ->
                    LazyListPerformance(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }

        }
    }
}