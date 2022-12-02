import React from 'react';
import BaseNavbar from '../../components/BaseNavBar/BaseNavbar';
import styles from './Mypage.module.css';
import Link from 'next/link';
import Image from 'next/image';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
	faCoins,
	faPenToSquare,
	faPencil,
} from "@fortawesome/free-solid-svg-icons";
import OrangeButton from "../../components/Buttons/orangeButton";
import { signOut, useSession } from "next-auth/react";
import { redirect } from "next/navigation";
import getUserInfo from "../../utils/helper/backendUserInfo";
import { headers } from "next/headers";
import verifyLogin from "../../utils/VerifyLogin";
import { SignOut } from "../../components/Buttons/SignOut";

const MyPage = async () => {
	// const session = useSession();
	const userInfo = await verifyLogin();
	console.log("🚀 ~ file: page.tsx:22 ~ MyPage ~ userInfo", userInfo)

	const session = {
		status: 'authenticated',
	};

	// if (session.status === 'authenticated')
	if (userInfo)
		return (
			<>
				<BaseNavbar />
				<div className={styles.myinfo}>
					<Link href={'/editmypage'}>
						<Image
							className={styles.myimg}
							alt="myimg"
							src="/img/myimg.png"
							width={70}
							height={70}
						/>
						닉네임 &#62;
					</Link>
					<h3 className={styles.id}>아이디</h3>

					<div className={styles.Wrapper}>
						<FontAwesomeIcon icon={faCoins} className={styles.coinfont} />
						<h3 className={styles.mycoin}>코인갯수</h3>
					</div>
					<hr className={styles.line}></hr>
				</div>

				<div className={styles.mycorner}>
					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon icon={faCoins} className={styles.fontimg} />
						<Link href={`/charge`}>
							<h2 className={styles.font}>코인 충전하기</h2>
						</Link>
					</div>
					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon icon={faPencil} className={styles.fontimg} />
						<Link href={`/myupload`}>
							<h2 className={styles.font}>내가 올린 클래스</h2>
						</Link>
					</div>

					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon icon={faPenToSquare} className={styles.fontimg} />
						<Link href={`/upload`}>
							<h2 className={styles.font}>강좌 개설하기</h2>
						</Link>
					</div>
					<SignOut />
				</div >
			</>
		);
	else {
		redirect("/");
	}
};

export default MyPage;
