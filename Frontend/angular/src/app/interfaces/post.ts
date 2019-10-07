import { Entity } from './entity';

export interface Post extends Entity {
    name: string;
    start_date?: string;
    description?: string;
    localisation?: string;
    image?: string;
    salary_min?: number;
    salary_max?: number;
    experience?: number;
    education?: string;
    job_location?: string;
    email?: string;
    domain_activity?: string;
    image_path?: string;
    created_by?: Entity | number;
}
