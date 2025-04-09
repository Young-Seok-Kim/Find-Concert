// Mapper.kt
package com.youngs.findconcert.data

import com.youngs.findconcert.data.local.ConcertEntity
import com.youngs.findconcert.data.remote.NetworkConcert
import com.youngs.findconcert.data.remote.NetworkConcertDetail
import com.youngs.findconcert.domain.model.Concert
import com.youngs.findconcert.domain.model.ConcertDetail

// Network → Domain
internal fun NetworkConcert.toDomain(): Concert = Concert(
    id = this.id,
    title = this.title,
    date = this.date,
    venue = this.venue,
    detailUrl = this.ticketUrl,
    imageUrl = this.imageUrl
)

internal fun NetworkConcertDetail.toDomain(): ConcertDetail = ConcertDetail(
    id = this.id,
    title = this.title,
    date = this.date,
    venue = this.venue,
    description = this.description,
    performers = this.performers,
    ticketUrl = this.ticketUrl,
    imageUrls = this.imageUrls
)

internal fun ConcertEntity.toDomainDetail(): ConcertDetail = ConcertDetail(
    id = this.id,
    title = this.title,
    date = this.date,
    venue = this.venue,
    description = this.description ?: "No description",  // 기본값 처리
    performers = this.performers.split(","),  // CSV → List 변환
    ticketUrl = this.ticketUrl,
    imageUrls = listOf(this.imageUrl ?: "")  // 단일 URL → List 변환
)

// Entity ↔ Domain
internal fun ConcertEntity.toDomain(): Concert = Concert(
    id = this.id,
    title = this.title,
    date = this.date,
    venue = this.venue,
    detailUrl = this.ticketUrl,
    imageUrl = this.imageUrl
)

internal fun Concert.toEntity(): ConcertEntity = ConcertEntity(
    id = this.id,
    title = this.title,
    date = this.date,
    venue = this.venue,
    ticketUrl = this.detailUrl,
    imageUrl = this.imageUrl,
    description = null,  // 도메인 모델에 없는 필드 → 기본값 처리
    performers = ""      // 도메인 모델에 없는 필드 → 기본값 처리
)
