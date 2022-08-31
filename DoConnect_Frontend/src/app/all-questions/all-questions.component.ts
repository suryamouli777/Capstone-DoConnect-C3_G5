import { Component, OnInit } from '@angular/core';
import { UserService } from './../user.service';
import { Question } from '../question';
import { User } from '../user';
import { Router } from '@angular/router';
import { ChatComponent } from '../chat/chat.component';


@Component({
  selector: 'app-all-questions',
  templateUrl: './all-questions.component.html',
  styleUrls: ['./all-questions.component.css']
})
export class AllQuestionsComponent implements OnInit {

  constructor( private userService:UserService , private router:Router) { }

  topic:any
  ngOnInit(): void {
    this.user=this.userService.giveUserData()
  }
  questions:Question[] | undefined

  user= new User()

 getQuestions(topic:any){
    this.userService.getQuestions(topic).subscribe((data)=>{
      this.questions=data
      this.topic = topic
    console.log("questions are "+this.questions)
    let allUniqueQuestions; // {question: [answers1, answr2], question: []}
    this.questions.forEach(question => {

      console.log(question.question)
      
    });
  })
 }

 sendQuestionToGetAnswer(questionId:number){
  this.userService.getQuestionId(questionId)
  this.router.navigate(['/get-answer'])

 }



}
