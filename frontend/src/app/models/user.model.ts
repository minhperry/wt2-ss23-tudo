export interface User {
    id : any;
    name: string;
    username: string;
    password: string;
    usertype: UserType;
}

export interface UserAuth {
    username: string;
    password: string;
}

export enum UserType {
    ADMIN = "admin",
    USER = "user"
}