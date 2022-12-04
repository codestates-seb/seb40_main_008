export interface UserInfoWithCoin {
	userId: number;
	userName: string;
	profileImage: string;
	email: string;
	totalCoin: number;
}

type IUserInfo = UserInfoWithCoin | null | undefined;

export default IUserInfo;
