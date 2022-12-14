'use client';
import React from 'react';
import styles from './BaseNavbar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useScrollBar } from '../../hooks/\bScrollBar/UseScrollBar';
import { useHasWindow } from '../../utils/hooks/useHasWindow';
import { useRouter } from 'next/navigation';

interface BaseNavbarProps {
	name?: string;
	page?: string;
}

const BaseNavbar = ({ name, page }: BaseNavbarProps) => {
	const router = useRouter();
	const { show } = useScrollBar();
	const hasWindow = useHasWindow();
	if (!hasWindow) {
		return null;
	}

	router.prefetch('/');

	return (
		<>
			{window.scrollY < 45 ? (
				<nav className={styles.firstNav}>
					{page === 'back' ? (
						<>
							<button
								onClick={() => router.back()}
								className={styles.leftbtn}
							>
								<FontAwesomeIcon
									icon={faAngleLeft}
									width={24}
									className={styles.font}
								/>
							</button>
							<p
								style={{
									marginTop: '12px',
									fontSize: '20px',
									marginLeft: '100px',
								}}
							>
								{name}
							</p>
						</>
					) : (
						<button
							onClick={() => router.push(`/`)}
							className={styles.leftbtn}
						>
							<FontAwesomeIcon
								icon={faHouse}
								width={24}
								className={styles.font}
							/>
						</button>
					)}
				</nav>
			) : (
				<nav className={`${show ? styles.baseNav : styles.change_nav} `}>
					{page === 'back' ? (
						<>
							<button
								onClick={() => router.back()}
								className={styles.leftbtn}
							>
								<FontAwesomeIcon
									icon={faAngleLeft}
									width={24}
									className={styles.font}
								/>
							</button>
							<p
								style={{
									marginTop: '12px',
									fontSize: '20px',
									marginLeft: '100px',
								}}
							>
								{name}
							</p>
						</>
					) : (
						<button
							onClick={() => router.push(`/`)}
							className={styles.leftbtn}
						>
							<FontAwesomeIcon
								icon={faHouse}
								width={24}
								className={styles.font}
							/>
						</button>
					)}
				</nav>
			)}
		</>
	);
};

export default BaseNavbar;
