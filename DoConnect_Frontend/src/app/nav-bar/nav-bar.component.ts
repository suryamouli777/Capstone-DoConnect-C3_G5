import { Component, OnInit } from '@angular/core';
import { UserService } from './../user.service';
import { Question } from './../question';
import { Router } from '@angular/router';
import { User } from '../user';
import { AdminService } from './../admin.service';
import { Admin } from '../admin';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private userService:UserService, private router:Router , private adminService:AdminService
  ) { }

  ngOnInit(): void {
    this.user=this.userService.giveUserData()
    this.admin=this.adminService.giveAdminData()
    console.log("logs from navbar")
    console.log(this.admin)
    console.log(this.user)
  }
  isSearched:boolean=false

  questions:Question[] | undefined

  response:any
  user=new User()
  admin = new Admin()
    getValue(values:string){
    if(values !=='')
    this.userService.searchQuestion(values).subscribe((data)=>{
      console.log(data)
      this.questions=data
      if(data.length==0){
        alert("No Question Found")
      }else{
      this.isSearched=true}
    })

  }
  sendQuestionToGetAnswer(id:number){
    console.log(id)
    this.userService.getQuestionId(id)
    this.router.navigate(['/get-answer'])
    this.isSearched=false

  }

  logout(){
    if(this.admin.id!=0){
      this.adminService.adminLogout(this.admin.id).subscribe((data)=>{
        this.response=(data)
        this.admin.id=0
        this.user.id=0
       },err =>{
        console.log("error called"+err)
        this.admin=new Admin()
        this.adminService.sendAdminData(this.admin)
      
        this.router.navigate(['/admin-login'])
       }
       )
    }

    if(this.user.id!=0){
      this.userService.userLogout(this.user.id).subscribe((data)=>{
        this.response=(data)
       },err =>{
        console.log("error called"+err)
        this.user=new User()
        this.userService.sendUserData(this.user)
        this.router.navigate(['/login'])
       }
       )
    }
  }
}

