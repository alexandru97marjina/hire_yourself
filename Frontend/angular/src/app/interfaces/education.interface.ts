import { EntityInterface } from '@interfaces/entity.interface';

export interface EducationInterface extends EntityInterface {
    specialityName?: string;
    studyGrade?: string;
    mixedName?: string;
}
