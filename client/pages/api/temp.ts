import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	const { body } = req;

	res.status(200).json({ text: 'Hello' });
}
