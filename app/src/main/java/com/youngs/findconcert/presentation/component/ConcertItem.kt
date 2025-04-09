// ConcertItem.kt  
package com.youngs.findconcert.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youngs.findconcert.domain.model.Concert

@Composable
fun ConcertItem(
    concert: Concert,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
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
