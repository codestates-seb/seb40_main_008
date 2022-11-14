import NextAuth from 'next-auth/next';
import GoogleProvider from 'next-auth/providers/google';

export default NextAuth({
	providers: [
		GoogleProvider({
			clientId: process.env.GOOGLE_CLIENT_ID as string,
			clientSecret: process.env.GOOGLE_CLIENT_SECRET as string,
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
