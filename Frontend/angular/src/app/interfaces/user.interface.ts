import { EntityInterface } from './entity.interface';

export interface UserInterface extends EntityInterface {
    email: string;
    first_name?: string;
    last_name?: string;
    address?: string;
    phone?: string;
    password?: string;
}
