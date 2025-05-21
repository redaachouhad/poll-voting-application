package com.votingapp.voting_app_backend.services;

import com.votingapp.voting_app_backend.models.OptionVote;
import com.votingapp.voting_app_backend.models.Poll;
import com.votingapp.voting_app_backend.repositories.PollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }


    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        //Get Poll from DB
        Poll poll = pollRepository.findById(pollId).orElseThrow(()->new RuntimeException("Poll Not Found"));
        // Get All Options.
        List<OptionVote> optionVotes = poll.getOptions();
        // If Index for vote is not valid , throw error
        if(optionIndex < 0 || optionIndex >= optionVotes.size()){
            throw new IllegalArgumentException("Invalid Option Index");
        }
        // Get Selected Option.
        OptionVote selectedOption = optionVotes.get(optionIndex);
        // Increment vote for selected option.
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        // Save incremented vote option into the database.
        pollRepository.save(poll);
    }
}
