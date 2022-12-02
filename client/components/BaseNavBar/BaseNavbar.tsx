'use client';
import React from 'react';
import styles from './BaseNavbar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleLeft } from '@fortawesome/free-solid-svg-icons';
import { useScrollBar } from '../../hooks/\bScrollBar/UseScrollBar';
import { useHasWindow } from '../../utils/hooks/useHasWindow';

const BaseNavbar = () => {
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
						<FontAwesomeIcon icon={faAngleLeft} width={24} className={styles.font} />
					</button>
				</nav>
			) : (
				<nav className={`${show ? styles.baseNav : styles.change_nav} `}>
					<button
						onClick={() => window.history.back()}
						className={styles.leftbtn}
					>
						<FontAwesomeIcon icon={faAngleLeft} width={24} className={styles.font} />
					</button>
				</nav>
			)}
		</>
	);
};

export default BaseNavbar;
