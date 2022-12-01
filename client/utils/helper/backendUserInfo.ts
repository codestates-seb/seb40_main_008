import { RequestCookie } from 'next/dist/server/web/spec-extension/cookies';
import IUserInfo from '../../types/user/userinfo';

const getUserInfo = async (
	cookie: RequestCookie | undefined | null | string
): Promise<IUserInfo> => {
	if (!cookie) return null;
	const token = cookie;

	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Authorization: `Bearer ${token}`,
		},
	});

	const body = await res.json();
	if (!body.data?.userName) {
		return null;
	}
	return body.data;
};

export default getUserInfo;
