import React from 'react';

const layout = ({ children }: any) => {
	// if (segment === 'login') return children;

	return (
		<>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</>
	);
};

export default layout;
