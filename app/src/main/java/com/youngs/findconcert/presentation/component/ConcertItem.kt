// ConcertItem.kt  
package com.youngs.findconcert.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.youngs.findconcert.domain.model.Concert

@Composable
fun HorizontalScrollableText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val scrollState = rememberScrollState()
    Row(modifier = modifier.horizontalScroll(scrollState)) {
        Text(text = text, style = style)
    }
}


@Composable
fun ConcertItem(concert: Concert) {

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 콘서트 포스터 (왼쪽)
        AsyncImage(
            model = concert.imageUrl,
            contentDescription = "Concert Poster",
            modifier = Modifier
                .size(100.dp) // 이미지 크기 조절
                .weight(0.3f), // Row에서 차지하는 비율
            contentScale = ContentScale.Crop // 이미지를 어떻게 채울지 설정
        )

        // 콘서트 정보 (오른쪽)
        Column(
            modifier = Modifier.weight(0.7f), // Row에서 차지하는 비율
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            HorizontalScrollableText(
                text = "장소: ${concert.venue}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "일시: ${concert.date}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}  
