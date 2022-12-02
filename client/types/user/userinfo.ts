interface UserInfo {
  userId: number;
  userName: string;
  profileImage: string;
  email: string;
  totalCoin: number;
}

type IUserInfo = UserInfo | null;

export default IUserInfo;
