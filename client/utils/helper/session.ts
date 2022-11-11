import { Session, unstable_getServerSession } from 'next-auth';

type EmptyObject = {
	[key: string]: never;
};

export async function getSession(cookie: string): Promise<Session | null> {
	console.log('🚀 ~ file: session.ts ~ line 8 ~ getSession ~ cookie', cookie);
	const response = await fetch(`${process.env.NEXTAUTH_URL}/api/auth/session`, {
		headers: { cookie },
	});

	console.log('🚀 ~ file: session.ts ~ line 7 ~ getSession ~ response', response);

	if (!response?.ok) {
		return null;
	}

	const session = await response.json();
	console.log('🚀 ~ file: session.ts ~ line 20 ~ getSession ~ session', session);

	return Object.keys(session).length > 0 ? session : null;
}
