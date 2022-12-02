import { getCookie } from 'cookies-next';

export async function fetchDelete(url: string, id: number) {
	const token = getCookie('accessToken');
	const requestOptions = {
		method: 'DELETE',
		headers: {
			Authorization: `Bearer ${token}`,
		},
	};

	try {
		const response = await fetch(`${url}${id}`, requestOptions);
		console.log('adsadsad', `${url}${id}`);
		return response.status;
	} catch (err) {
		console.error(err);
	}
}

export async function fetchEditChapter(
	url: string,
	thumbnail: string,
	chapterOrder: string,
	title: string,
	Id: any
) {
	console.log('🚀 ~ file: fetchDelete.ts:29 ~ Id', Id);
	console.log('🚀 ~ file: fetchDelete.ts:30 ~ thumbnail', thumbnail);
	const formData = new FormData();
	formData.append('thumbnail', thumbnail);
	formData.append('chapterOrder', chapterOrder);
	formData.append('title', title);

	const token = getCookie('accessToken');
	const requestOptions = {
		method: 'PATCH',
		headers: {
			Authorization: `Bearer ${token}`,
		},
		body: formData,
	};
	console.log('🚀 ~ file: fetchDelete.ts:40 ~ url', url);
	try {
		const response = await fetch(
			`https://pioneroroom.com/auth/contents/chapter/${Id}`,
			requestOptions
		);
		return response.status;
	} catch (err) {
		console.error(err);
	}
}

export async function fetchEditClass(
	videoFile: File,
	title: string,
	docsFile: File,
	details: string,
	uploadClassid: number
) {
	const token = getCookie('accessToken');
	const requestOptions = {
		method: 'PATCH',
		headers: {
			Authorization: `Bearer ${token}`,
		},
		body: JSON.stringify({ videoFile, title, docsFile, details }),
	};
	try {
		const response = await fetch(
			`http://localhost:8080/auth/contents/chapter/${uploadClassid}`,
			requestOptions
		);
		return response.status;
	} catch (err) {
		console.error(err);
	}
}
