export interface HomeContent {
	contentsList: ICategorySearchResult[];
}

export interface ICategorySearchResult {
	contentsId: number;
	title: string;
	thumbnail: string;
	categories: string;
	users: Users;
}

export interface Users {
	usersId: number;
	userName: string;
	profileImage: string;
}

// export interface Content {
// 	contentsId: number;
// 	title: string;
// 	thumbnail: string;
// 	categories: string;
// 	users: Users;
// }

// export interface Users {
// 	usersId: number;
// 	userName: string;
// 	profileImage?: string;
// }
