import { UserInterface } from '@interfaces/user.interface';

export class AuthHelper {

    public static getMe(): UserInterface {
        return localStorage.getItem('me') ? (JSON.parse(localStorage.getItem('me')) as UserInterface) : null;
    }

    public static setMe(user: UserInterface) {
        localStorage.setItem('me', (user ? JSON.stringify(user) : null));
    }

    public static getAuthenticated() {
        return Boolean(JSON.parse(localStorage.getItem('authenticated')));
    }

    public static setAuthenticated(value: boolean) {
        localStorage.setItem('authenticated', JSON.stringify(value));
    }
}
