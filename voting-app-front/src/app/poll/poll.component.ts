import {Component, OnInit} from '@angular/core';
import {PollService} from '../poll.service';
import {Poll} from '../poll.models';
import {FormsModule} from '@angular/forms';
@Component({
  selector: 'app-poll',
  imports: [
    FormsModule
  ],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit{

  polls: Poll[] = [];
  newPoll: Poll = {
    question: "",
    options: [
      {voteOption: '', voteCount:0},
      {voteOption: '', voteCount:0}
    ],
  };

  constructor(private pollService: PollService) {
  }

  loadPolls(){
    this.pollService.getPolls().subscribe({
      next: (data) => this.polls = data,
      error: error => console.error("Error fetching polls: ",error),
    })
  }

  ngOnInit(): void {
    this.loadPolls();
  }

  trackByIndex(index: number){
    return index;
  }

  resetPoll(){
    this.newPoll = {
      question: "",
      options: [
        {voteOption: '', voteCount:0},
        {voteOption: '', voteCount:0}
      ],
    };
  }

  createPoll() {
    this.pollService.createPoll(this.newPoll).subscribe({
      next: (createdPoll) =>{
        this.polls.push(createdPoll);
        this.resetPoll();
      },
      error: error => console.error("Error creating Poll: ",error),
    })
  }


  vote(pollId: number | undefined, optionIndex: number) {
    this.pollService.vote(pollId as number, optionIndex).subscribe({
      next: (data)=>{
        const selectedPoll: Poll | undefined = this.polls.find(p=>p.id === pollId);
        if(selectedPoll){
          selectedPoll.options[optionIndex].voteCount++;
        }
      },
      error: error => console.error("Error voting Poll: ",error),
    })
  }

  addAnotherOption() {
    this.newPoll.options.push({voteOption: '', voteCount:0})
  }

  removeOption(optionIndex: number) {
    this.newPoll.options.splice(optionIndex, 1);
  }
}
