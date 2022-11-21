import { NextApiRequest, NextApiResponse } from 'next';
import { getToken } from 'next-auth/jwt';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
	console.log('req', req.cookies);
	const token = await getToken({ req, secret: process.env.JWT_SECRET as string });

	console.log('🚀 ~ file: hello.ts ~ line 6 ~ handler ~ token', token);
	console.log('json web token', JSON.stringify(token, null, 2));

	res.status(200).json({ text: 'Hello' });
}
