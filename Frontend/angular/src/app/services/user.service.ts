import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { UserInterface } from '@interfaces/user.interface';

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

    public updateUser(user: UserInterface) {
        return this.httpService.put(this.userApi.update(user.id), user);
    }

    public createUser(user: UserInterface) {
        return this.httpService.post(this.userApi.create, user);
    }

    public deleteUser(user: UserInterface) {
        return this.httpService.delete(this.userApi.delete(user.id));
    }

}
