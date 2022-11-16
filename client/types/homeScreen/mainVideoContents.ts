export interface HomeContent {
	contentsList: Content[];
}

export interface Content {
	id: number;
	title: string;
	thumbnail: string;
	categories: string;
	users: Users;
}

export interface Users {
	id: number;
	username: string;
	profileImage?: string;
}
