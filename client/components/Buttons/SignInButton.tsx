'use client';
import { signIn, signOut } from 'next-auth/react';
import React from 'react';
import Image from 'next/image';
import styles from './Button.module.css'
import naverlogo from '../../public/img/naverlogo.png'
import kakaologo from '../../public/img/kakaologo.png';
import googlelogo from '../../public/img/googlelogo.webp';
import adminlogo from '../../public/img/myimg.png'

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

			<button className={styles.adminbtn} onClick={() => signIn()}>
				<div className={styles.adminbtnWrapper}>
					<Image
						className={styles.adminlogo}
						src={adminlogo}
						alt='admin logo'
						width={50}
					/>
					관리자 로그인
				</div>
			</button>

		</div>
	);
};

export default SignInButton;
