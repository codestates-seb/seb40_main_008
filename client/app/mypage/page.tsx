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
} from '@fortawesome/free-solid-svg-icons';
import { redirect } from 'next/navigation';
import verifyLogin from '../../utils/VerifyLogin';
import { SignOut } from '../../components/Buttons/SignOut';
import AlertCheck from './AlertCheck';

const MyPage = async ({ searchParams: { status } }: any) => {
	const userInfo = await verifyLogin();

	if (!userInfo) redirect('/');

	return (
		<>
			{/* <AlertCheck status={status} /> */}
			<BaseNavbar />
			<div className={styles.mypageWrapper}>
				<div className={styles.myinfo}>
					<Image
						style={{ borderRadius: '50%' }}
						className={styles.myimg}
						alt="myimg"
						src={userInfo.profileImage}
						width={70}
						height={70}
					/>
					{userInfo.userName}
					<h3 className={styles.id}>{userInfo.email}</h3>

					<div className={styles.Wrapper}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.coinfont}
						/>
						<h3 className={styles.mycoin}>
							보유 코인: {userInfo.totalCoin}
						</h3>
					</div>
					<hr className={styles.line}></hr>
				</div>

				<div className={styles.mycorner}>
					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon
							icon={faCoins}
							width={24}
							className={styles.fontimg}
						/>
						<Link href={`/charge`}>
							<h2 className={styles.font}>코인 충전하기</h2>
						</Link>
					</div>
					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon
							icon={faPencil}
							width={24}
							className={styles.fontimg}
						/>
						<Link href={`/mypage/uploadclass`}>
							<h2 className={styles.font}>내가 올린 클래스</h2>
						</Link>
					</div>

					<div className={styles.CourseWrapper}>
						<FontAwesomeIcon
							icon={faPenToSquare}
							width={24}
							className={styles.fontimg}
						/>
						<Link href={`/upload`}>
							<h2 className={styles.font}>강좌 개설하기</h2>
						</Link>
					</div>
				</div>
			</div>
			<SignOut />
		</>
	);
};

export default MyPage;
