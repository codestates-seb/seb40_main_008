import React from 'react';
import { signIn, signOut, useSession } from 'next-auth/react';
import { unstable_getServerSession } from 'next-auth';
import { getSession } from '../../utils/helper/session';
import SignInButton from '../../components/Buttons/SignInButton';
import { headers } from 'next/headers';
import Link from 'next/link';

const LoginPage = async () => {
	const session = await getSession(headers().get('cookie') ?? '');
	return (
		<>
			<Link href={'/'}>Home</Link>
			<p>User: {session?.user?.email || 'nothing'}</p>
			<SignInButton isSignIn={false} />
			<SignInButton isSignIn={true} />
		</>
	);
};

export default LoginPage;