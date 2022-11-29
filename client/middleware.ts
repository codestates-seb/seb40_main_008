import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';

export function middleware(request: NextRequest) {
	// console.log(
	// 	'🚀 ~ file: middleware.ts ~ line 5 ~ middleware ~ request',
	// 	request
	// );
	// console.log(
	// 	'🚀 ~ file: middleware.ts ~ line 6 ~ middleware ~ request.cookies',
	// 	request.cookies
	// );

	return NextResponse.next();
}
