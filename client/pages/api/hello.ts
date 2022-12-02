import { NextApiRequest, NextApiResponse } from 'next';
import { NextRequest, NextResponse } from 'next/server';
import { getToken } from 'next-auth/jwt';
import { deleteCookie } from 'cookies-next';

export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	// res.cookies.set('accessToken', '', {
	// 	httpOnly: false,
	// 	maxAge: 0, // 0 second hours in seconds
	// });
	deleteCookie('accessToken', { res, req });
	res.status(200).json({ message: 'success' });
	return res;
}
// export default async function handler(
// 	req: NextApiRequest,
// 	res: NextApiResponse
// ) {
// 	console.log('🚀 ~ file: hello.ts:16 ~ req', req);
// 	res.getHeader
// 	res.status(200).json({ name: 'John Doe' });
// 	return res;
// }
