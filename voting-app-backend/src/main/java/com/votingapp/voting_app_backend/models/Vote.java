package com.votingapp.voting_app_backend.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote {
    private Long pollId;
    private int optionIndex;
}
