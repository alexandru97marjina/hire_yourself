import { EntityInterface } from './entity.interface';
import { PostInterface } from '@interfaces/post.interface';
import { ExperienceInterface } from '@interfaces/experience.interface';
import { EducationInterface } from '@interfaces/education.interface';

export interface UserInterface extends EntityInterface {
    email: string;
    first_name?: string;
    last_name?: string;
    address?: string;
    phone?: string;
    password?: string;
    active?: boolean;
    age?: number;
    cvPath?: string;
    education?: EducationInterface;
    experience?: ExperienceInterface[];
    favoritePosts?: PostInterface[];
    graduationYear?: number;
    postLimit?: number;
    posts?: PostInterface[];
    activityField?: string;
}
