import { getCookie } from 'cookies-next';

export async function patchWish(contentId: number, wish: boolean) {
	const token = getCookie('accessToken');

	const requestOptions = {
		method: 'POST',
		headers: {
			Authorization: `Bearer ${token}`,
		},
	};
	try {
		const response = await fetch(
			`https://pioneroroom.com/auth/${contentId}/wish`,
			requestOptions
		);
		return response.status;
	} catch (err) {
		console.error(err);
		return undefined;
	}
}
