import { EntityInterface } from './entity.interface';

export interface PostInterface extends EntityInterface {
    title: string;
    dateCreated?: number;
    dateExpired?: number;
    dateUpdated?: number;
    description?: string;
    jobLocation?: string;
    imagePath?: string;
    salaryMin?: number;
    salaryMax?: number;
    minExperience?: number;
    maxExperience?: number;
    educationId?: EntityInterface | number;
    email?: string;
    activityId?: EntityInterface | number;
    userId?: EntityInterface | number;
    active?: boolean;
}
