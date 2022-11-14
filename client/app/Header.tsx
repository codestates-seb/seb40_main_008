import React from 'react';

interface HeaderProp {
	children: React.ReactNode;
	htmlProp: JSX.IntrinsicElements['html'];
}

const Header = ({ children, ...props }: HeaderProp) => {
	return (
		<html {...props}>
			<head>
				<meta name="viewport" content="width=device-width,initial-scale=1" />
				<title>올인원 프로필 링크, 리틀리</title>
			</head>
			<body>{children}</body>
		</html>
	);
};

export default Header;
