'use client';
import React from 'react';
import styles from './CategoryNavbar.module.css';
import { useScrollBar } from '../../hooks/\bScrollBar/UseScrollBar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleLeft, faHouse } from '@fortawesome/free-solid-svg-icons';
import Link from 'next/link';
import { useHasWindow } from '../../utils/hooks/useHasWindow';

const CategoryNavBar = () => {
	const { show } = useScrollBar();
	const hasWindow = useHasWindow();
	if (!hasWindow) {
		return null;
	}
	return (
		<>
			{window.scrollY < 45 ? (
				<nav className={styles.firstNav}>
					<button
						onClick={() => window.history.back()}
						className={styles.leftbtn}
					>
						<FontAwesomeIcon icon={faAngleLeft} className={styles.font} />
					</button>
					<button className={styles.rightbtn}>
						<Link href={'/'}>
							<FontAwesomeIcon icon={faHouse} className={styles.font} />
						</Link>
					</button>
				</nav>
			) : (
				<nav className={`${show ? styles.baseNav : styles.change_nav} `}>
					<button
						onClick={() => window.history.back()}
						className={styles.leftbtn}
					>
						<FontAwesomeIcon icon={faAngleLeft} className={styles.font} />
					</button>
					<button className={styles.rightbtn}>
						<Link href={'/'}>
							<FontAwesomeIcon icon={faHouse} className={styles.font} />
						</Link>
					</button>
				</nav>
			)}
		</>
	);
};

export default CategoryNavBar;
