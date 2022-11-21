import React from 'react';

const layout = ({ children }: any) => {
	return (
		<html>
			<head></head>
			<body>
				<div>
					<nav style={{ width: '100%', height: '100px', backgroundColor: 'green' }}>
						Layout
					</nav>
					{children}
				</div>
			</body>
		</html>
	);
};

export default layout;
