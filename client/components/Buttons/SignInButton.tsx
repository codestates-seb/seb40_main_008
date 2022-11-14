'use client';
import { signIn, signOut } from 'next-auth/react';
import React from 'react';

interface SignInButtonProps {
	isSignIn: boolean;
}

const SignInButton = ({ isSignIn }: SignInButtonProps) => {
	const handleSignin = async () => {
		signIn();
	};
	return isSignIn ? (
		<button onClick={() => signOut()}>sign out</button>
	) : (
		<button onClick={() => signIn()}>sign in</button>
	);
};

export default SignInButton;
