// Concert.kt  
package com.youngs.findconcert.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Concert(
    val id: String,
    val title: String,
    val date: String,
    val venue: String,
    val detailUrl: String,
    val imageUrl: String? = null
) {
    // 동등성 비교를 위한 equals/hashCode 자동 생성 (data class 특성)  
    // toString() 자동 생성: "Concert(id=..., title=...)" 형식  
    // copy() 메소드 지원: val concert2 = concert1.copy(title="새 제목")  
}  
