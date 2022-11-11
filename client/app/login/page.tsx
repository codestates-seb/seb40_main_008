import React from 'react';
import { signIn, signOut, useSession } from 'next-auth/react';
import { unstable_getServerSession } from 'next-auth';
import { getSession } from '../../utils/helper/session';
import SignInButton from '../../components/Buttons/SignInButton';
import { headers } from 'next/headers';

const LoginPage = async () => {
	// const session = await getSession(headers().get('cookie') ?? '');
	const session = await unstable_getServerSession();
	console.log('🚀 ~ file: page.tsx ~ line 9 ~ LoginPage ~ session', session);
	const isSession = session?.user?.email;
	// BUG: expression evaluation null returns 'undefiend' is not valid JSON.
	if (isSession) {
		return (
			<div>
				<p style={{ color: 'white' }}>
					you are not signed in
					<SignInButton isSignIn={false} />
				</p>
			</div>
		);
	} else {
		<div>
			<p style={{ color: 'white' }}>welcome, {JSON.stringify(session)}</p>
			<SignInButton isSignIn={true} />
		</div>;
	}
};

export default LoginPage;
