import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	// console.log('req', req);
	// const token = await getToken({ req, secret: process.env.JWT_SECRET as string });

	const accessToken = req.query.access_token;
	const refreshToken = req.query.refresh_token;
	if (accessToken)
		res.setHeader(
			'Set-Cookie',
			'accessToken=' + accessToken + '; Path=/; Secure; SameSite=None'
		);

	res.redirect(`/`);
}
