package com.example.moviereview.services;

import com.example.moviereview.dtos.ReviewDTO;
import com.example.moviereview.storage.movie.Movie;
import com.example.moviereview.storage.movie.MovieRepository;
import com.example.moviereview.storage.reviewer.Reviewer;
import com.example.moviereview.storage.reviewer.ReviewerRepository;
import com.example.moviereview.storage.reviewer.review.Review;
import com.example.moviereview.storage.reviewer.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewerRepository reviewerRepository;
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public ReviewService(ReviewerRepository reviewerRepository, ReviewRepository reviewRepository,
                         MovieRepository movieRepository) {
        this.reviewerRepository = reviewerRepository;
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public ResponseEntity<?> addReview(ReviewDTO.Create dto) {
        if (movieRepository.findById(dto.getMovieId()).isPresent()) {
            Movie movie = movieRepository.findById(dto.getMovieId()).get();
            if (reviewerRepository.findById(dto.getReviewerId()).isPresent()) {
                Reviewer reviewer = reviewerRepository.findById(dto.getReviewerId()).get();
                Review review = new Review();
                review.setMovie(movie);
                review.setTitle(dto.getTitle());
                review.setContent(dto.getContent());
                review.setReviewer(reviewer);
                return ResponseEntity.ok().body(reviewRepository.save(review));
            }
            return ResponseEntity.badRequest().body("Error: Reviewer not found");
        }
        return ResponseEntity.badRequest().body("Error: Movie not found");
    }
}
