export interface ICategorySearchResult {
    id: number;
    contentsId: number;
    title: string;
    thumbnail: string;
    categories: string;
    users: Users;
}

export interface Users {
    usersId: number;
    username: string;
    profileImage: string;
}
