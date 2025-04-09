package com.youngs.findconcert.data.local

import com.youngs.findconcert.domain.model.Concert

// 크롤링한 사이트들의 타이틀을 리스트별로 보여주기 위한 데이터 클래스
data class ConcertList(
    val title: String,
    val concerts: List<Concert>
)

