export interface ICategorySearchResult {
    contentsId: number;
    title: string;
    thumbnail: string;
    likesCount: number;
    categories: string;
    users: Users;
}

export interface Users {
    usersId: number;
    userName: string;
    profileImage: string;
}
