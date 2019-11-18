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
        create: '/api/posts-list',
        delete: (id: number) => `/api/posts/${id}`,
        getList: '/api/posts',
        apply: (postId: number, userId: number) => `/api/apply/${postId}/${userId}`,
        accept: (postId: number, userId: number) => `/api/accept/${postId}/${userId}`,
        addToFavorite: (postId: number, userId: number) => `/api/favorites/${postId}/${userId}`,
        deleteFavorite: (postId: number, userId: number) => `/api/favorites/${postId}/${userId}`,
        getFavorites: (userId: number) => `/api/favorites/${userId}`,
        userCreatedPosts: (userId: number) => `/api/user-posts/${userId}`,
        userSubscribedPosts: (userId: number) => `/api/user-subscribed-posts/${userId}`,
    },
    Management: {
        register: '/api/register',
        resetPassword: '/api/reset-password',
        requestPassword: '/api/reset-password-request',
        login: '/api/login_check',
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
