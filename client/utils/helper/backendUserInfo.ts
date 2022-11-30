import IUserInfo from '../../types/user/userinfo';
// import { extractToken } from './extractToken';

const getUserInfo = async (token: string): Promise<IUserInfo> => {
	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Authorization: `Bearer ${token}`,
		},
	});
	const body = await res.json();
	if (!body.data?.userName) {
		console.log('user info not found');
		return null;
	}
	return body.data;
};

export default getUserInfo;
