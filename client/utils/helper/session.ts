import { Session, unstable_getServerSession } from 'next-auth';
import { getToken } from 'next-auth/jwt';

type EmptyObject = {
	[key: string]: never;
};

export async function getSession(cookie: string): Promise<Session | null> {
	const response = await fetch(`${process.env.NEXTAUTH_URL}/api/auth/session`, {
		headers: { cookie },
	});
	console.log('🚀 ~ file: session.ts ~ line 12 ~ getSession ~ response', response);

	if (!response?.ok) return null;
	const session = await response.json();
	return Object.keys(session).length > 0 ? session : null;
}
