import React from 'react';

interface LayoutProp {
	children: React.ReactNode;
}

const layout = ({ children }: LayoutProp) => {
	return <div>{children}</div>;
};

export default layout;
