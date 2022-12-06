import { cookies } from 'next/headers';
import getUserInfo from './helper/backendUserInfo';

const verifyLogin = async () => {
	const token = cookies().get('accessToken')?.value;
	if (!token) {
		return;
	}
	return await getUserInfo(token);
};

export default verifyLogin;
