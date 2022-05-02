package com.example.moviereview.storage.reviewer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
}
