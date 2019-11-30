import { PostInterface } from '@interfaces/post.interface';

export class SearchHelper {
    public static applyFilters(data: PostInterface[], filters: any = {}) {
        if (filters && Object.values(filters).length) {
            data = data.filter(item => item.title.toLowerCase().includes(filters.name.toLowerCase()));

            const startDate = new Date(filters.startDate).getTime();
            const endDate = new Date(filters.endDate).getTime();

            if (!isNaN(startDate)) {
                data = data.filter(item => item.dateCreated >= startDate);
            }

            if (!isNaN(endDate)) {
                data = data.filter(item => item.dateCreated <= endDate);
            }

            if (filters.location && filters.location.length) {
                data = data.filter(item => item.jobLocation === filters.location);
            }

            if (filters.user) {
                data = data.filter(item => item.userId === filters.user);
            }

            if (filters.activity) {
                data = data.filter(item => item.activityId === filters.activity);
            }

            if (filters.education) {
                data = data.filter(item => item.educationId === filters.education);
            }
        }

        return data;
    }
}
