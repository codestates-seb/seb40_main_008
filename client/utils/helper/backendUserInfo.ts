import IUserInfo from '../../types/user/userinfo';

const getUserInfo = async (cookie: string): Promise<IUserInfo> => {
	const token = cookie;
	console.log('token', token);

	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		method: 'GET',
		headers: {
			'Content-Type': 'application/json',
			Authorization: `Bearer ${token}`,
		},
	});

	const body = await res.json();
	console.log(
		'🚀 ~ file: backendUserInfo.ts ~ line 18 ~ getUserInfo ~ body',
		body
	);
	if (!body.data?.userName) {
		console.log('user info not found');
		return null;
	}
	return body.data;
};

export default getUserInfo;