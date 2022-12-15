export interface HomeContent {
  contentsList: ICategorySearchResult[];
}

export interface ICategorySearchResult {
  contentsId: number;
  title: string;
  thumbnail: string;
  categories: string;
  users: Users;
  details: string;
  tutorDetail: string;
  price: number;
}

export interface Users {
  usersId: number;
  userName: string;
  profileImage: string;
}
