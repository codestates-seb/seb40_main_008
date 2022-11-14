import Link from 'next/link';
import React from 'react';
import QuestionCard from '../components/Card/QuestionCard';

const getQuestionList = async () => {
	const response = await fetch(`https://pioneroroom.com/questionlist`);
	const data = await response.json();
	return data;
};

const page = async ({ Question }: any) => {
	// const imageArr = await getCarouselImages();
	const data = await getQuestionList();

	return (
		<div>
			<Link href={'/login'}>Login page</Link>
			{/* <Carousel carousel={imageArr} /> */}
			{data.data.map((e: any) => {
				return <QuestionCard key={e.questionId} question={e} />;
			})}
		</div>
	);
};

export default page;
