import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  constructor( private userService:UserService , private router:Router) { }

  ngOnInit(): void {
    this.user=this.userService.giveUserData()
    if(this.user.id !==0){
      alert("Already Logged")
      this.router.navigate(['/user'])
    }
  }

  user=new User()

  userRegister(data:any) {

    this.user.email=data.email
    this.user.firstName=data.fName
    this.user.lastName=data.lName
    this.user.password=data.password
    this.user.phoneNumber=data.phoneNumber
		this.userService.userRegister(this.user).subscribe((data)=>{
        this.user=data
        alert("user registered successfully")
        this.router.navigate(['/login'])
     },err =>{
      alert("email already registered")
      this.router.navigate(['/login'])
     }

     )
	}

}
