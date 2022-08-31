import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ChatService } from './../chat.service';
import { MessageDTO } from './../messageDTO';
import { UserService } from './../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(private chatService: ChatService, private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.user = this.userService.giveUserData()

    if (this.user.id == 0) {
      alert("Login Required")
      this.router.navigate(['/login'])
    }
  }
  messageText = ""
  messageDTO = new MessageDTO()
  messages: MessageDTO[] | undefined
  user = new User()

  sendMessage(msg: string) {
    this.messageDTO.fromUser = this.user.firstName
    this.messageDTO.message = msg

    this.messageDTO.userId = this.user.id
    
    console.log(this.user.id, 'hi', this.messageDTO.userId)
    this.chatService.sendMessage(this.messageDTO).subscribe((data) => {
      this.messageDTO = data
      this.getMessage()
    })
  }

  getMessage() {
    this.chatService.getMessage().subscribe((data) => {
      this.messages = data

    })


  }
}
