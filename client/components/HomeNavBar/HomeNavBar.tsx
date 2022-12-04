import React from 'react';
import Link from 'next/link';
import Image from 'next/image';
import styles from './HomeNavBar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import IUserInfo from '../../types/user/userinfo';

interface HomeNavBarProps {
	userInfo: IUserInfo | undefined;
}

const HomeNavBar = ({ userInfo }: HomeNavBarProps) => {
	const myprofileImg = userInfo?.profileImage || '/img/myimg.png';

	return (
		<>
			<nav className={`${styles.nav} `}>
				<div className="logo">
					<FontAwesomeIcon
						icon={faBars}
						width={24}
						className={styles.font}
					/>
					<Link href={'/'} className={styles.logo}>
						CLASS4989
					</Link>
				</div>
				<div>
					{userInfo ? (
						<Link href={'/mypage'}>
							<Image
								sizes="(max-width: 768px) 100vw,
							(max-width: 1200px) 50vw,
							33vw"
								className="myimg"
								alt="myimg"
								src={myprofileImg}
								width={35}
								height={35}
								style={{ marginTop: '5px', borderRadius: '50%' }}
							/>
						</Link>
					) : (
						<Link className={styles.login} href={'/login'}>
							Login
						</Link>
					)}
				</div>
			</nav>
		</>
	);
};

export default HomeNavBar;
