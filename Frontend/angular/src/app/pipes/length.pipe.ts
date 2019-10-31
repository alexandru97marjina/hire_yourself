import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'length'})
export class LengthPipe implements PipeTransform {
    transform(value: string, length: number = 15): string {
        if (typeof value !== 'string' && typeof value !== 'number') {
            return value;
        }

        value = String(value);
        return value.length > length ? (value.substr(0, length) + '...') : value;
    }
}
