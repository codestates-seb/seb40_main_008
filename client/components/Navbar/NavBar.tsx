import React, { useState } from 'react';
import Link from 'next/link';
import styles from './NavBar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import { unstable_getServerSession } from 'next-auth';
import { getSession } from '../../utils/helper/session';
import { headers } from 'next/headers';

const NavBar = async () => {
	const session = await getSession(headers().get('cookie') ?? '');
	console.log('🚀 ~ file: NavBar.tsx ~ line 12 ~ NavBar ~ session', session);

	return (
		<nav className={styles.nav}>
			<div>
				<FontAwesomeIcon icon={faBars} className={styles.font} />
				<Link className={styles.logo} href={'/'}>
					clss4989
				</Link>
			</div>
			{JSON.stringify(session?.user?.email)}
			<div>{session ? <Link href={'/'}>Logout</Link> : <Link href={'/login'}>Login</Link>}</div>
		</nav>
	);
};

export default NavBar;
