import { EntityInterface } from '@interfaces/entity.interface';

export interface ExperienceInterface extends EntityInterface {
    companyName: string;
    dateStarted?: number;
    dateEnded?: number;
    monthsOfExperience?: number;
}
