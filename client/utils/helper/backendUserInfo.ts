import { RequestCookie } from 'next/dist/server/web/spec-extension/cookies';
import IUserInfo from '../../types/user/userinfo';

const getUserInfo = async (
	cookie: RequestCookie | undefined | null
): Promise<IUserInfo> => {
	if (!cookie) return null;
	const token = cookie.value;

	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Authorization: `Bearer ${token}`,
		},
	});

	const body = await res.json();
	// console.log(
	// 	'🚀 ~ file: backendUserInfo.ts ~ line 18 ~ getUserInfo ~ body',
	// 	body
	// );
	if (!body.data?.userName) {
		return null;
	}
	return body.data;
};

export default getUserInfo;
