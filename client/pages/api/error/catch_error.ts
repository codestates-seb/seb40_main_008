import { NextApiRequest, NextApiResponse } from 'next';
import { deleteCookie, removeCookies } from 'cookies-next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	console.log('res: ', res);
	console.log('catch_error: ', req);
	const body = JSON.parse(req.body);
	console.log('body_error: ', body.error);

	res.status(200).json({ message: 'success' });
}

/**
 * url: request.url
 * time: request.
 * errorMsg:
 * statusCode:
 */
