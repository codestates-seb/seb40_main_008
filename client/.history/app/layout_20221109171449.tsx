import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
	params: any;
}

const layout = ({ children, params }: any) => {
	console.log(
		'🚀 ~ file: layout.tsx ~ line 9 ~ layout ~ children',
		children.props.childProp.segment
	);

	const { segment } = children.props.childProp;

	if (segment === 'login') return children;

	return (
		<div>
			<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>Layout</nav>
			{children}
		</div>
	);
};

export default layout;
