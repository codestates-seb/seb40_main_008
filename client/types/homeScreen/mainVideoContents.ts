export interface Content {
	contentsId: number;
	title: string;
	thumbnail: string;
	categories: string;
	users: Users;
}

export interface Users {
	usersId: number;
	userName: string;
	profileImage?: string;
}
