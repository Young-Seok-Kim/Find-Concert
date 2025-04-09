package com.youngs.findconcert.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "concerts")
data class ConcertEntity(
    @PrimaryKey val id: String,
    val title: String,
    val date: String,
    val venue: String,
    val description: String?,  // 상세 설명 필드 추가
    val performers: String,     // CSV 형식 저장 (예: "아이유,악동뮤지션")
    val ticketUrl: String,
    val imageUrl: String?       // 단일 이미지 URL
)