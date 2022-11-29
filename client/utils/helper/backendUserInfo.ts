const getUserInfo = async (cookie: string) => {
	const indexOfCookieStart =
		cookie.indexOf('accessToken') + 'accessToken='.length;
	const indexOfCookieEnd = cookie.indexOf(';', indexOfCookieStart);
	const token = cookie.slice(indexOfCookieStart, indexOfCookieEnd);

	const res = await fetch('https://pioneroroom.com/auth/userinfo', {
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});

	const userInfo = await res.json();
	return userInfo.data;
};

export default getUserInfo;
