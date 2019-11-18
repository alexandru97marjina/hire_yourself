export class EmptyDataResponse {
    status: string;
    data: object[];
    message: string;
    errors: string[];
    constructor(errors: string[] = []) {
        this.status = null;
        this.data = [];
        this.message = 'empty response';
        this.errors = errors;
    }
}
