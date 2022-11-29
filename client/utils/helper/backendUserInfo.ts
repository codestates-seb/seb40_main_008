import IUserInfo from '../../types/user/userinfo';
import { extractToken } from './extractToken';

const getUserInfo = async (cookie: string): Promise<IUserInfo> => {
	const token = extractToken(cookie);
	console.log(
		'🚀 ~ file: backendUserInfo.ts ~ line 6 ~ getUserInfo ~ token',
		token
	);

	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});

	const body = await res.json();
	console.log(
		'🚀 ~ file: backendUserInfo.ts ~ line 18 ~ getUserInfo ~ body',
		body
	);
	if (!body.userName) {
		console.log('user info not found');
		return null;
	}
	const { userInfo } = body;
	return userInfo;
};

export default getUserInfo;

// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWtkbHN6MjFAZ21haWwuY29tIiwic3ViIjoiYWtkbHN6MjFAZ21haWwuY29tIiwiaWF0IjoxNjY5NzA3ODk4LCJleHAiOjE2Njk3MDk2OTh9.KKMhyzfs0eH8qf1ktbUzo16B92EgsNnnNgfeuqTPamqpQBhcm3ldCbSSKiy0W5m39kOahWw0VhhgVCH4yAP7QA

// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiYWtkbHN6MjFAZ21haWwuY29tIiwic3ViIjoiYWtkbHN6MjFAZ21haWwuY29tIiwiaWF0IjoxNjY5NzA3ODk4LCJleHAiOjE2Njk3MDk2OTh9.KKMhyzfs0eH8qf1ktbUzo16B92EgsNnnNgfeuqTPamqpQBhcm3ldCbSSKiy0W5m39kOahWw0VhhgVCH4yAP7QA
