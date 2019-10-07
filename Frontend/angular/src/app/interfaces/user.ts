import { Entity } from './entity';

export interface User extends Entity {
    email: string;
    first_name?: string;
    last_name?: string;
    address?: string;
    phone?: string;
    password?: string;
}
