'use client';

import React, { useState } from 'react';
import Link from 'next/link';

const NavBar = () => {
	const [isLogin, setIsLogin] = useState(false);
	console.log('isLogin', isLogin);
	return (
		<nav style={{ width: '100%', height: '50px', backgroundColor: 'white' }}>
			<div
				style={{
					width: '100%',
					height: '100%',
					display: 'flex',
					justifyContent: 'center',
					alignItems: 'center',
				}}
			>
				{isLogin ? <Link href={'/'}>Logout</Link> : <Link href={'/login'}>Login</Link>}
			</div>
		</nav>
	);
};

export default NavBar;
