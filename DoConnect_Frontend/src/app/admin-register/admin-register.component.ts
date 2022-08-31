import { Component, OnInit } from '@angular/core';
import { Admin } from '../admin';
import { AdminService } from './../admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-register',
  templateUrl: './admin-register.component.html',
  styleUrls: ['./admin-register.component.css']
})
export class AdminRegisterComponent implements OnInit {

  constructor(private adminService: AdminService,private router:Router) { }

  ngOnInit(): void {

    this.admin=this.adminService.giveAdminData()
    if(this.admin.id !==0){
      alert("Already Logged")
      this.router.navigate(['/admin'])
    }
  }

  admin= new Admin();

  adminRegister(data:any){

   this.admin.email= data.email
   this.admin.firstName=data.fName
   this.admin.lastName=data.lName
    this.admin.password=data.password
    this.admin.phoneNumber=data.mNumber

     this.adminService.adminRegister(this.admin).subscribe((data)=>{
      this.admin=data
      alert("Admin Added")
      this.router.navigate(['/admin'])
     },err =>{
      alert("email already registered")
      this.router.navigate(['/admin'])
     })

    }
}
