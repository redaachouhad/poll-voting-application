package com.votingapp.voting_app_backend.repositories;

import com.votingapp.voting_app_backend.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
