import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { ActivityInterface } from '@interfaces/activity.interface';
import { Observable } from 'rxjs';
import { ResponseInterface } from '@interfaces/response.interface';

@Injectable({
    providedIn: 'root'
})
export class ActivityService {

    readonly activityApi = Api.Activity;

    constructor(private httpService: HttpService) {
    }

    public getActivity(id: number) {
        return this.httpService.get(this.activityApi.getOne(id)) as Observable<ResponseInterface>;
    }

    public getList() {
        return this.httpService.get(this.activityApi.getList) as Observable<ResponseInterface>;
    }

    public updateActivity(activity: ActivityInterface) {
        return this.httpService.put(this.activityApi.update(activity.id), activity) as Observable<ResponseInterface>;
    }

    public createActivity(activity: ActivityInterface) {
        return this.httpService.post(this.activityApi.create, activity) as Observable<ResponseInterface>;
    }

    public deleteActivity(activity: ActivityInterface) {
        return this.httpService.delete(this.activityApi.delete(activity.id)) as Observable<any>;
    }
}
