export default function RootLayout({ children }: { children: React.ReactNode }) {
	return <body>{children}</body>;
}

import React from 'react';

const layout = ({ children }: any) => {
	return (
		<html>
			<head></head>
			<div>
				<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
				{children}
			</div>
		</html>
	);
};

export default layout;
