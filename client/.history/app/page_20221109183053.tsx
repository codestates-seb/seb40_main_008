import React from 'react';
import QuestionCard from '../src/components/Card/QuestionCard';

// const getQuestionList = async () => {
// 	const response = await fetch('https://pioneroroom.com/questionlist');
// 	const data = await response.json();
// 	return data;
// };

const page = async ({ Question }: any) => {
	// const data = await getQuestionList();

	return (
		<div>
			<h2>hello</h2>
			{/* {data.data.map((e: any) => {
				return <QuestionCard key={e.questionId} question={e} />;
			})} */}
		</div>
	);
};

export default page;
