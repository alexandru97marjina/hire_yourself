export const Api = {
    User: {
        getOne: (id: number) => `/api/users/${id}`,
        update: (id: number) => `/api/users/${id}`,
        create: '/api/users',
        delete: (id: number) => `/api/users/${id}`,
        getList: '/api/users',
    },
    Post: {
        getOne: (id: number) => `/api/posts/${id}`,
        update: (id: number) => `/api/posts/${id}`,
        create: '/api/posts',
        delete: (id: number) => `/api/posts/${id}`,
        getList: '/api/posts',
        apply: (postId: number, userId: number) => `/api/apply/${postId}/${userId}`,
        accept: (postId: number, userId: number) => `/api/accept/${postId}/${userId}`,
        addToFavorite: (userId: number, postId: number) => `/api/favorites/${userId}/${postId}`,
        deleteFavorite: (userId: number, postId: number) => `/api/favorites/${userId}/${postId}`,
        getFavorites: (userId: number) => `/api/favorites/${userId}`,
        userCreatedPosts: (userId: number) => `/api/user-posts/${userId}`,
        userSubscribedPosts: (userId: number) => `/api/user-subscribed-posts/${userId}`,
    },
    Management: {
        register: '/api/register',
        resetPassword: '/api/reset-password',
        requestPassword: '/api/security/users/password-request/',
        login: '/api/security/users/log',
    },
    File: {
        upload: '/api/file/uploadFile',
        uploadMultiple: '/api/file/uploadMultipleFiles',
        download: (filename: string) =>  `/api/file/getFilePath/${filename}`,
    },
    Education: {
        getList: '/api/educations',
        getOne: (id: number) => `/api/educations/${id}`,
        create: '/api/educations',
        delete: (id: number) => `/api/educations/${id}`,
        update: (id: number) => `/api/educations/${id}`,
    },
    Activity: {
        getList: '/api/activity',
        getOne: (id: number) => `/api/activity/${id}`,
        create: '/api/activity',
        delete: (id: number) => `/api/activity/${id}`,
        update: (id: number) => `/api/activity/${id}`,
    }
};

export class ApiHelper {
    public static queryParams(object: object = {}): string {
        let str = '';
        if (Object.values(object).length) {
            str += '?';
            str += (Object.keys(object).map(key => `${key}=${object[key]}`).join('&'));
        }

        return str;
    }
}
