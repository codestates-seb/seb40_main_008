import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	const accessToken = req.query.access_token;
	const refreshToken = req.query.refresh_token;
	if (accessToken)
		res.setHeader(
			'Set-Cookie',
			'accessToken=' + accessToken + '; Path=/; Secure; SameSite=None'
		);

	res.redirect(`/`);
}
