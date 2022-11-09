import React from 'react';

const template = (props: any) => {
	console.log('🚀 ~ file: template.tsx ~ line 4 ~ template ~ props', props);

	return (
		<div>
			<h1>template</h1>
			{props.children}
		</div>
	);
};

export default template;
