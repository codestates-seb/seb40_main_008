'use client';
import styles from './TabNavigator.module.css';
import Link from 'next/link';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse, faMagnifyingGlass, faPlay } from '@fortawesome/free-solid-svg-icons';

import React from 'react';

interface TabNavigatorProps {
	activeLink: string;
}

const TabNavigator = ({ activeLink }: TabNavigatorProps) => {
	return (
		<div className={styles.tab}>
			<Link href={`/`}>
				<div className={`${styles.tabItem} ${activeLink === 'home' && styles.active}`}>
					<FontAwesomeIcon icon={faHouse} width={24} className={styles.Icon} />
					<p className={styles.font}>홈</p>
				</div>
			</Link>
			<Link href={`/search`}>
				<div className={`${styles.tabItem} ${activeLink === 'search' && styles.active}`}>
					<FontAwesomeIcon icon={faMagnifyingGlass} width={24} className={styles.Icon} />
					<p className={styles.font}>검색</p>
				</div>
			</Link>
			<Link href={`/myclass`}>
				<div className={`${styles.tabItem} ${activeLink === 'myclass' && styles.active}`}>
					<FontAwesomeIcon icon={faPlay} width={24} className={styles.Icon} />
					<p className={styles.font}>내 클래스</p>
				</div>
			</Link>
		</div>
	);
};

export default TabNavigator;
