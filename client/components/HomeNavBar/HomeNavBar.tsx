'use client';
import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Image from 'next/image';
import { useScrollBar } from '../../hooks/\bScrollBar/UseScrollBar';
import styles from './HomeNavBar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { useSession } from 'next-auth/react';
import IUserInfo from '../../types/user/userinfo';

interface HomeNavBarProps {
	userInfo: IUserInfo;
}

const HomeNavBar = ({ userInfo }: HomeNavBarProps) => {
	// const session = useSession();
	const session = {
		status: 'authenticated',
	};
	const { show } = useScrollBar();
	// console.log(window.scrollY);

	const scrollTopBtn = () => {
		window.scrollTo({
			top: 0,
			behavior: 'smooth',
		});
	};

	return (
		<>
			{window.scrollY < 60 ? (
				<nav className={styles.firstNav}>
					<div className={styles.logowrapper}>
						<Link href={'/categories'}>
							<FontAwesomeIcon icon={faBars} className={styles.font} />
						</Link>

						<button className={styles.logo} onClick={scrollTopBtn}>
							CLASS4989
						</button>
					</div>
					<div>
						{userInfo ? (
							<Link href={'/mypage'}>
								<Image
									className="myimg"
									alt="myimg"
									src="/img/myimg.png"
									width={35}
									height={35}
								/>
							</Link>
						) : (
							<Link className={styles.login} href={'/login'}>
								Login
							</Link>
						)}
					</div>
				</nav>
			) : (
				<nav className={`${show ? styles.nav : styles.change_nav} `}>
					<div className="logo">
						<FontAwesomeIcon icon={faBars} className={styles.font} />
						<button className={styles.logo} onClick={scrollTopBtn}>
							CLASS4989
						</button>
					</div>
					<div>
						{userInfo ? (
							<Link href={'/mypage'}>
								<Image
									className="myimg"
									alt="myimg"
									src="/img/myimg.png"
									width={40}
									height={40}
								/>
							</Link>
						) : (
							<Link className={styles.login} href={'/login'}>
								Login
							</Link>
						)}
					</div>
				</nav>
			)}
		</>
	);
};

export default HomeNavBar;
