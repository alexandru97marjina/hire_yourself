import { Injectable } from '@angular/core';
import { Api } from '@helpers/api.helper';
import { HttpService } from '@services/http.service';
import { Observable } from 'rxjs';
import { FileResponseInterface } from '@interfaces/fileResponse.interface';

@Injectable({
    providedIn: 'root'
})
export class FileService {

    readonly fileApi = Api.File;

    constructor(private httpService: HttpService) {
    }

    public upload(file: File) {
        const formData = new FormData();
        formData.append('file', file);
        return this.httpService.post(this.fileApi.upload, formData);
    }

    public download(filename: string): Observable<FileResponseInterface> {
        return this.httpService.get(this.fileApi.download(filename));
    }
}
