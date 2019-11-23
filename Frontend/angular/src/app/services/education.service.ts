import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { EducationInterface } from '@interfaces/education.interface';

@Injectable({
    providedIn: 'root'
})
export class EducationService {

    readonly educationApi = Api.Education;

    constructor(private httpService: HttpService) {
    }

    public getEducation(id: number) {
        return this.httpService.get(this.educationApi.getOne(id));
    }

    public getList() {
        return this.httpService.get(this.educationApi.getList);
    }

    public updateEducation(education: EducationInterface) {
        return this.httpService.put(this.educationApi.update(education.id), education);
    }

    public createEducation(education: EducationInterface) {
        return this.httpService.post(this.educationApi.create, education);
    }

    public deleteEducation(education: EducationInterface) {
        return this.httpService.delete(this.educationApi.delete(education.id));
    }
}
