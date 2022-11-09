import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
}

const layout = ({ children }: LayoutProp) => {
	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</div>
	);
};

export default layout;
