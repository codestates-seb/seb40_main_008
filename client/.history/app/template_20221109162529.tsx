import React from 'react';

const template = (props: any) => {
	console.log('🚀 ~ file: template.tsx ~ line 4 ~ template ~ props', props);

	return <div>{props.children}</div>;
};

export default template;
