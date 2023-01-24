//https://angular.io/guide/http
import { Injectable } from '@angular/core'; //Injection dependency.
import { HttpClient } from '@angular/common/http'; //Methods to perform HTTP requests
import { Observable } from 'rxjs'; //Provides support for passing messages between parts of your application
//import { catchError, retry } from 'rxjs/operators'; 

import { Person } from './person';
import { environment } from 'src/environments/environment'; //Ensures that the build and serve commands can find the configurations for specific build targets

//Decorator that marks a class as available to be provided and injected as a dependency.
@Injectable({
  providedIn: 'root'
})  
export class PersonService {

  //front-end app communicating with server over the HTTP protocol
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient){}

  //Returns Performed CRUD Operations
  public getPersons(): Observable<Person[]> {
    //Requesting data from server, http.get<Config>()
    return this.http.get<Person[]>(`${this.apiServerUrl}/person/all`); //calls person.ts interface for config
  }

  //Sending modified data to the server; PUT, POST, DELETE
  public addPerson(person: Person): Observable<Person> {
    return this.http.post<Person>(`${this.apiServerUrl}/person/add`, person);
  }

  public updatePerson(person: Person): Observable<Person> {
    return this.http.put<Person>(`${this.apiServerUrl}/person/update`, person);
  }

  public deletePerson(personId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/person/delete/${personId}`);
  }
}