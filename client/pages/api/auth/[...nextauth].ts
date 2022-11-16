import NextAuth from 'next-auth/next';
import GoogleProvider from 'next-auth/providers/google';
import KakaoProvider from "next-auth/providers/kakao"
import NaverProvider from "next-auth/providers/naver";

export default NextAuth({
	providers: [
		GoogleProvider({
			clientId: process.env.GOOGLE_CLIENT_ID as string,
			clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
		}),
		KakaoProvider({
			clientId: process.env.KAKAO_CLIENT_ID as string,
			clientSecret: process.env.KAKAO_CLIENT_SECRET as string,
		}),
		NaverProvider({
			clientId: process.env.NAVER_CLIENT_ID as string,
			clientSecret: process.env.NAVER_CLIENT_SECRET as string,
		}),
	],
	session: {
		strategy: 'jwt',
	},
	secret: process.env.JWT_SECRET as string,
	jwt: {
		maxAge: 30 * 24 * 60 * 60, // 30 days
	},
});

// next cookies next-auth.session-token.
