import { EntityInterface } from './entity.interface';
import { Validators } from '@angular/forms';

export interface PostInterface extends EntityInterface {
    title: string;
    dateCreated?: string;
    dateExpired?: string;
    description?: string;
    jobLocation?: string;
    imagePath?: string;
    salaryMin?: number;
    salaryMax?: number;
    minExperience?: number;
    maxExperience?: number;
    educationId?: number;
    email?: string;
    activityId?: string;
    userId?: EntityInterface | number;
    active?: boolean;
}
