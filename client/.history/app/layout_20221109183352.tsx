import React from 'react';

const layout = ({ children }: any) => {
	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</div>
	);
};

export default layout;
