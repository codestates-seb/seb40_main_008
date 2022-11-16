'use client';
import { signIn, signOut } from 'next-auth/react';
import React from 'react';
import { useEffect, useState } from 'react'

// *kakao login,logout 후 다시 login 시도 시 오류 발생*
// Unhandled Runtime Error
// Error: Hydration failed because the initial UI does not match what was rendered on the server.
// 1. secret 모드에서 발생(로그인 쿠키가 남아있지 않을 때)
//const [element, setElement] = useState<HTMLElement | null>(null);


interface SignInButtonProps {
	isSignIn: boolean;
}

const SignInButton = ({ isSignIn }: SignInButtonProps) => {

	console.log(isSignIn);

	const handleSignin = async () => {
		signIn();
	};
	return isSignIn ? (
		<button onClick={() => signOut()}>sign out</button>
	) : (
		<button onClick={() => signIn()}>sign in</button>
		// <button onClick={() => signIn('kakao')}>sign with kakao</button>
	);
};

export default SignInButton;
