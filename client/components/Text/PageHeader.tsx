import React, { CSSProperties } from 'react';

interface Props {
	text: string;
	style?: CSSProperties;
}

const PageHeader = ({ text, style }: Props) => {
	return (
		<div
			style={{
				...{
					display: 'flex',
					justifyContent: 'center',
					alignItems: 'center',
				},
				...style,
			}}
		>
			<h2>{text}</h2>
		</div>
	);
};

export default PageHeader;
