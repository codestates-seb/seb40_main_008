import React from 'react';

const LoginLayout = ({ children }: any) => {
	console.log('🚀 ~ file: layout.tsx ~ line 4 ~ LoginLayout ~ children', children);

	return (
		<div>
			<h2>LoginLayout</h2>
			{children}
		</div>
	);
};

export default LoginLayout;
