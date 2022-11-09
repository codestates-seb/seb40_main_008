import React from 'react';
import Button from '../src/components/Buttons/Button';
import QuestionCard from '../src/components/Card/QuestionCard';

const getQuestionList = async () => {
	const response = await fetch('https://pioneroroom.com/questionlist');
	const data = await response.json();
	console.log(data.data[0]);
	return data;
};

const page = async ({ Question }: any) => {
	const data = await getQuestionList();

	return (
		<div>
			{data.data.map((e) => {
				return <QuestionCard key={e.id} question={e} />;
			})}
		</div>
	);
};

export default page;
