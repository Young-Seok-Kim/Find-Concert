package com.youngs.findconcert

// MainActivity.kt
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.youngs.findconcert.presentation.crawler.InterparkCrawler
import com.youngs.findconcert.presentation.screen.ConcertListScreen
import com.youngs.findconcert.presentation.viewmodel.ConcertViewModel
import com.youngs.findconcert.presentation.viewmodel.InterparkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConcertTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: InterparkViewModel = viewModel() // ViewModel 인스턴스 생성
                    val concertLists = viewModel.concertLists.collectAsState().value

                    ConcertListScreen(concertLists = concertLists)
                }
            }
        }
    }


}

