'use client';
import { signIn, signOut } from 'next-auth/react';
import React from 'react';
import Image from 'next/image';
import styles from './Button.module.css'
import naverlogo from '../../public/img/naverlogo.png'
import kakaologo from '../../public/img/kakaologo.png';
import googlelogo from '../../public/img/googlelogo.webp';

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
		<div className={styles.loginbtnWrapper}>

			<button className={styles.googlebtn} onClick={() => signIn('google')}>
				<div className={styles.googlebtnWrapper}>
					<Image
						className={styles.googlelogo}
						src={googlelogo}
						alt='google logo'
						width={25}
					/>
					Sign with in Google
				</div>
			</button>

			<button className={styles.kakaobtn} onClick={() => signIn('kakao')}>
				<div className={styles.kakaobtnWrapper}>
					<Image
						className={styles.kakaologo}
						src={kakaologo}
						alt='kakao logo'
						width={30}
					/>
					카카오 로그인
				</div>
			</button>

			<button className={styles.naverbtn} onClick={() => signIn('naver')}>
				<div className={styles.naverbtnWrapper}>
					<Image
						className={styles.naverlogo}
						src={naverlogo}
						alt='naver logo'
						width={50}
					/>
					네이버 로그인
				</div>
			</button>

		</div>
	);
};

export default SignInButton;
