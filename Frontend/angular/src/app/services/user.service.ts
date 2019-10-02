import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { User } from '@interfaces/user';

@Injectable({
    providedIn: 'root'
})
export class UserService {

    readonly userApi = Api.User;

    constructor(private httpService: HttpService) {
    }

    public getUser(id: number) {
        return this.httpService.get(this.userApi.getOne(id));
    }

    public getList() {
        return this.httpService.get(this.userApi.getList);
    }

    public updateUser(user: User) {
        return this.httpService.put(this.userApi.update(user.id), user);
    }

    public createUser(user: User) {
        return this.httpService.post(this.userApi.create, user);
    }

    public deleteUser(user: User) {
        return this.httpService.delete(this.userApi.delete(user.id));
    }

}
