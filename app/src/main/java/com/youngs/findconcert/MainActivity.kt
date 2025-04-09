package com.youngs.findconcert

// MainActivity.kt
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.youngs.findconcert.presentation.screen.ConcertListScreen
import com.youngs.findconcert.presentation.viewmodel.InterparkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val crawler = InterparkCrawler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("메인 액티비티","시작")
        startCrawling()

        setContent {
            ConcertTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConcertListScreen()
                }
            }
        }
    }

    private fun startCrawling() {
        Log.d("startCrawl","ㅈㄱㄴ")
        lifecycleScope.launch {
            Log.d("startCrawl","ㅈㄱㄴ2")
            val result = crawler.crawlConcerts()
            result.forEach { concert ->
                Log.d("CrawledData", """  
                    인터파크
                    제목: ${concert.title}  
                    날짜: ${concert.date}  
                    장소: ${concert.venue}
                """.trimIndent())
            }
        }
    }
}

