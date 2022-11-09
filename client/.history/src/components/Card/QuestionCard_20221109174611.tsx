import Link from 'next/link';
import React from 'react';

const QuestionCard = ({ question }: any) => {
	return (
		<>
			<div style={{ width: '50%', height: '100px' }}>
				<h2>{question.Title}</h2>
				<Link href={`/detail/${question.questionId}`}></Link>
			</div>
		</>
	);
};

export default QuestionCard;
