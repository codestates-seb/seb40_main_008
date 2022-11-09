import React from 'react';
import Button from '../src/components/Buttons/Button';
import QuestionCard from '../src/components/Card/QuestionCard';

const getQuestionList = async () => {
	const response = await fetch('https://pioneroroom.com/questionlist');
	const data: Question[] = await response.json();
	return data;
};

interface PageProps {
	Question: Question[];
}

const page = async ({ Question }: PageProps) => {
	const data = await getQuestionList();

	return (
		<div>
			{data.map((e) => {
				return <QuestionCard key={e.id} question={e} />;
			})}
		</div>
	);
};

export default page;
