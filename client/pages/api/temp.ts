import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	console.log('req@@', req);
	const { body } = req;
	console.log('🚀 ~ file: temp.ts ~ line 8 ~ body', body);

	res.status(200).json({ text: 'Hello' });
}
