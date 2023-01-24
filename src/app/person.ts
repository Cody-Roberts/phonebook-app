//Specifing the data types to return to backend; response object type
export interface Person {
    id: number;
    firstName: string;
    middleInitial: string;
    lastName: string;
    phoneNumber: string;
    email: string;
    address: string;
  }