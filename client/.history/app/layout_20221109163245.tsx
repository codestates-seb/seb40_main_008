import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
}

const layout = ({ children }: LayoutProp) => {
	console.log('🚀 ~ file: layout.tsx ~ line 8 ~ layout ~ children', children);
	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</div>
	);
};

export default layout;
