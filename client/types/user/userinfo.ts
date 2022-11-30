interface UserInfo {
	userId: number;
	userName: string;
	profileImage: string;
	email: string;
}

type IUserInfo = UserInfo | null;

export default IUserInfo;
