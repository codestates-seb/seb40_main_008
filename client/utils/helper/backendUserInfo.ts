import IUserInfo from '../../types/user/userinfo';
// import { extractToken } from './extractToken';

const getUserInfo = async (cookie: string): Promise<IUserInfo> => {
	const token = cookie;
	console.log('token', token);
	// console.log('cookie', cookie);
	// console.log(
	// 	'🚀 ~ file: backendUserInfo.ts ~ line 6 ~ getUserInfo ~ token',
	// 	token
	// );

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
	if (!body.data.userName) {
		console.log('user info not found');
		return null;
	}
	return body.data;
};

export default getUserInfo;

// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiamh3b245MzA0QGdtYWlsLmNvbSIsInN1YiI6Impod29uOTMwNEBnbWFpbC5jb20iLCJpYXQiOjE2Njk3ODE4NjQsImV4cCI6MTY2OTc4MzY2NH0.g0II9j7VdXIFiHNtQEfCHisCB9v3L69kPueizVcqkR07H9uHhQQpSZ9sV4WJT5uRV5UMSqOsXvk1IRRMACqtaQ
// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiamh3b245MzA0QGdtYWlsLmNvbSIsInN1YiI6Impod29uOTMwNEBnbWFpbC5jb20iLCJpYXQiOjE2Njk3ODE4NjQsImV4cCI6MTY2OTc4MzY2NH0.g0II9j7VdXIFiHNtQEfCHisCB9v3L69kPueizVcqkR07H9uHhQQpSZ9sV4WJT5uRV5UMSqOsXvk1IRRMACqtaQ
