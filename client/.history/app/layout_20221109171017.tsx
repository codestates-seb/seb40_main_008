import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
	params: any;
}

const layout = ({ children, params }: LayoutProp) => {
	console.log('🚀 ~ file: layout.tsx ~ line 9 ~ layout ~ params', params);

	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</div>
	);
};

export default layout;
