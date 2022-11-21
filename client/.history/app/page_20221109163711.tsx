import React from 'react';

const getQuestionList = async () => {
	const response = await fetch('https://pioneroroom.com/questionlist');
	const data = await response.json();
	console.log('🚀 ~ file: page.tsx ~ line 6 ~ getQuestionList ~ data', data);
};

const page = async () => {
	const data = await getQuestionList();

	return <div>page</div>;
};

export default page;
