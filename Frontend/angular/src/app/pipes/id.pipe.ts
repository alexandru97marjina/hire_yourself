import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'id'})
export class IdPipe implements PipeTransform {
    transform(value: any): string {
        if (!value) {
            return '';
        }

        let result: any = '';

        if (Array.isArray(value)) {
            result = value.map(item => item.hasOwnProperty('id') ? item.id : Object.values(item).pop());
            return '[' +  result.join(', ') + ']';
        }

        if (typeof value !== 'object') {
            return value;
        }

        if (value.hasOwnProperty('id')) {
            return value.id;
        }

        Object.entries(value).forEach(item => {
            result = item[1];
        });

        return result;
    }
}
