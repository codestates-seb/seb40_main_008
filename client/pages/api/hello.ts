import { NextApiRequest, NextApiResponse } from 'next';
import { NextRequest, NextResponse } from 'next/server';
import { getToken } from 'next-auth/jwt';

// export default async function handler(req: NextRequest, res: NextResponse) {
// 	console.log('🚀 ~ file: hello.ts:6 ~ handler ~ req', req);
// 	const url = req.nextUrl;
// 	console.log('🚀 ~ file: hello.ts:8 ~ handler ~ url', url);
// 	NextResponse.redirect(`${url}`);
// 	NextResponse.json({ name: 'John Doe' });
// }
export default async function handler(
	req: NextApiRequest,
	res: NextApiResponse
) {
	console.log('🚀 ~ file: hello.ts:16 ~ req', req);
	res.status(200).json({ name: 'John Doe' });
	return res;
}
