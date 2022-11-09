import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
	params: any;
}

const layout = ({ children, params }: any) => {
	console.log('🚀 ~ file: layout.tsx ~ line 9 ~ layout ~ children', children);

	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
		</div>
	);
};

export default layout;
