import { getCookie } from 'cookies-next';
import { UserInfoWithCoin } from '../../types/user/userinfo';

export const getUserInfoClient = async () => {
	const token = getCookie('accessToken');
	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});
	const { data }: { data: UserInfoWithCoin } = await res.json();
	return data;
};
