import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
}

const layout = ({ children }: LayoutProp) => {
	return (
		<div>
			<h1>Layout</h1>
			{children}
		</div>
	);
};

export default layout;
