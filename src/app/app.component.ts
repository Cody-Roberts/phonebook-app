import { Component, OnInit } from '@angular/core';
import { Person } from './person';
import { PersonService } from './person.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  //Binding data model
  public persons: Person[] = []; 
  public editPerson: Person | undefined;
  public deletePerson: Person | undefined;
  title: String = 'Phonebook app';

  //Initializing instance of service components
  constructor(private personService: PersonService){
    this.persons = [];
  }

  //Intializes component for function
  ngOnInit() {
    this.getPersons();
  }

  public getPersons(): void {
    //subscribe returns the incoming data from the backend server
    this.personService.getPersons().subscribe({
      next: (response: Person[]) => {
        this.persons = response; //response is our list of entries
        console.log(this.persons);
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message); //consoles out error message
      }
    });
  }
  
  public onAddPerson(addForm: NgForm): void {
    //close button click for add entry box
    document.getElementById('add-person-form')?.click();
    this.personService.addPerson(addForm.value).subscribe({
      next: (response: Person) => {
        console.log(response);
        this.getPersons();
        addForm.reset();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    });
  }

  public onUpdatePerson(person: Person): void {
    this.personService.updatePerson(person).subscribe({
      next: (response: Person) => {
        console.log(response);
        this.getPersons();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  public onDeletePerson(personId: number): void {
    this.personService.deletePerson(personId).subscribe({
      next: (response: void) => {
        console.log(response);
        this.getPersons();
      },
      error: (error: HttpErrorResponse) => {
        alert(error.message);
      }
    });
  }

  //Search box, searches through array of persons
  public searchPersons(key: string): void {
    console.log(key);
    const results: Person[] = [];
    for (const person of this.persons) {
      if (person.firstName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || person.middleInitial.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || person.lastName.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || person.phoneNumber.toLowerCase().indexOf(key.toLowerCase()) !== -1
      || person.email.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || person.address.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(person);
      }
    }
    this.persons = results;
    if (results.length === 0 || !key) {
      this.getPersons();
    }
  }

  //Directs which action will be targeted by inputs from buttons
  public onOpenModal(person: Person, mode: string,): void {
    const container = document.getElementById('main-container'); //main-container id created to manage container
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    // if (mode === 'add') {
    //   button.setAttribute('data-target', '#addPersonModal');
    // }
    if (mode === 'edit') {
      this.editPerson = person;
      //sets target to html edit section
      button.setAttribute('data-target', '#updatePersonModal');
    }
    else if (mode === 'delete') {
      this.deletePerson = person;
      //sets target to html delete section
      button.setAttribute('data-target', '#deletePersonModal');
    }
    container!.appendChild(button);
    button.click();
  }
  
}