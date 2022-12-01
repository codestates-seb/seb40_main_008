import IUserInfo from '../../types/user/userinfo';

<<<<<<< HEAD
const getUserInfo = async (token: string): Promise<IUserInfo> => {
=======
const getUserInfo = async (cookie: string): Promise<IUserInfo> => {
	const token = cookie;

>>>>>>> 16300ec58e2f89900829c89212fe29459737b571
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

<<<<<<< HEAD
export default getUserInfo;
=======
export default getUserInfo;
>>>>>>> 16300ec58e2f89900829c89212fe29459737b571
