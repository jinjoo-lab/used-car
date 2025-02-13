package sundaystudy.kr.usedcar.module.review.controller

import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sundaystudy.kr.usedcar.global.dto.IdResponse
import sundaystudy.kr.usedcar.module.review.dto.request.ReviewRequest
import sundaystudy.kr.usedcar.module.review.dto.request.ReviewUpdateRequest
import sundaystudy.kr.usedcar.module.review.dto.response.ReviewResponse
import sundaystudy.kr.usedcar.module.review.service.ReviewService
import java.util.*

@RestController
@RequestMapping("reviews")
class ReviewController(
    private val reviewService: ReviewService,
) {
    @PostMapping
    fun saveReview(@RequestBody @Valid reviewRequest: ReviewRequest): ResponseEntity<IdResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(reviewRequest))

    @GetMapping
    fun getAllReview(pageable: Pageable): ResponseEntity<List<ReviewResponse>> =
        ResponseEntity.ok(reviewService.getAllReviews(pageable))

    @GetMapping("{id}")
    fun getReview(@PathVariable id: UUID): ResponseEntity<ReviewResponse> =
        ResponseEntity.ok(reviewService.getReview(id))

    @GetMapping("members/{memberId}")
    fun getReviewsByMemberId(@PathVariable memberId: UUID): ResponseEntity<List<ReviewResponse>> =
        ResponseEntity.ok(reviewService.getAllReviewsByMemberId(memberId))

    @PutMapping
    fun updateReview(@RequestBody @Valid reviewUpdateRequest: ReviewUpdateRequest): ResponseEntity<Unit> {
        reviewService.updateReview(reviewUpdateRequest)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("{id}")
    fun deleteReview(@PathVariable id: UUID): ResponseEntity<Unit> {
        reviewService.deleteReview(id)
        return ResponseEntity.noContent().build()
    }
}
