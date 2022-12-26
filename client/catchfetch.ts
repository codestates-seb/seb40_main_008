export default async function catchfetch(
	input: RequestInfo,
	init?: RequestInit | undefined,
	occur?: string
): Promise<Response> {
	const res = await fetch(input, init);
	try {
		if (!res.ok) {
			if (!occur) throw new Error(res.url + ' ' + 'Component not described');
			throw new Error(res.url + ' ' + occur);
		}
	} catch (err) {
		const error = err as any;
		fetch(`http://localhost:3000/api/error/catch_error`, {
			method: 'POST',
			body: JSON.stringify({
				error: error.message,
				statusCode: res.status,
			}),
		});
	} finally {
		return res;
	}
}
