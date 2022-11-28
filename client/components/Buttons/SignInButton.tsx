"use client";
import { signIn, signOut } from "next-auth/react";
import React from "react";
import Image from "next/image";
import styles from "./Button.module.css";
import naverlogo from "../../public/img/naverlogo.png";
import kakaologo from "../../public/img/kakaologo.png";
import googlelogo from "../../public/img/googlelogo.webp";
import adminlogo from "../../public/img/myimg.png";
import { Link } from "react-router-dom";

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

			<button className={styles.googlebtn}>
				<a href="https://pioneroroom.com/oauth2/authorization/google">
					<div className={styles.googlebtnWrapper}>
						<Image
							className={styles.googlelogo}
							src={googlelogo}
							alt='google logo'
							width={25}
						/>
						<p className={styles.btn_p}>Sign with in Google</p>
					</div>
				</a>
			</button>


			<button className={styles.kakaobtn}>
				<a href="https://pioneroroom.com/oauth2/authorization/kakao">
					<div className={styles.kakaobtnWrapper}>
						<Image
							className={styles.kakaologo}
							src={kakaologo}
							alt='kakao logo'
							width={30}
						/>
						<p className={styles.btn_p}>카카오 로그인</p>
					</div>
				</a>
			</button>

			<button className={styles.naverbtn}>
				<a href="https://pioneroroom.com/oauth2/authorization/naver">
					<div className={styles.naverbtnWrapper}>
						<Image
							className={styles.naverlogo}
							src={naverlogo}
							alt='naver logo'
							width={50}
						/>
						<p className={styles.btn_naver_p}>네이버 로그인</p>
					</div>
				</a>
			</button>

			<button className={styles.adminbtn} onClick={() => signIn()}>
				<a href="https://pioneroroom.com/oauth2/authorization/naver">
					<div className={styles.adminbtnWrapper}>
						<Image
							className={styles.adminlogo}
							src={adminlogo}
							alt='admin logo'
							width={50}
						/>
						<p className={styles.btn_p}>관리자 로그인</p>
					</div>
				</a>
			</button>

		</div>
	);
};

export default SignInButton;
