import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'ucfirst'})
export class UcfirstPipe implements PipeTransform {
    public transform(text: string, firstLetterOnly: boolean = true): string {
        if (!text) {
            return '';
        }

        let rest = text.slice(1);

        if (!firstLetterOnly) {
            rest = rest.toLowerCase();
        }

        return text[0].toUpperCase() + rest;
    }
}
