import { removeCookies } from 'cookies-next';
import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    removeCookies('accessToken', { res, req });
    res.status(200).json({ message: 'success' });
    return res;
}