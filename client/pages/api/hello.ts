import { NextApiRequest, NextApiResponse } from 'next';
import { deleteCookie, removeCookies } from 'cookies-next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	// deleteCookie('accessToken', { res, req });
	removeCookies('accessToken', { res, req });
	res.status(200).json({ message: 'success' });
	console.log('🚀 ~ file: hello.ts:14 ~ res', res);
	return res;
}
