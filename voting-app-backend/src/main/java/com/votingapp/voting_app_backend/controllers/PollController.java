package com.votingapp.voting_app_backend.controllers;

import com.votingapp.voting_app_backend.models.Poll;
import com.votingapp.voting_app_backend.models.Vote;
import com.votingapp.voting_app_backend.services.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")

public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll){
        return ResponseEntity.status(HttpStatus.CREATED).body(pollService.createPoll(poll));
    }

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls(){
        return ResponseEntity.status(HttpStatus.OK).body(pollService.getAllPolls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id){
        return pollService.getPoll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST
    // VOTE
    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }


}
