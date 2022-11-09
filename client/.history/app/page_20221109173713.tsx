import React from 'react';
import Button from '../src/components/Buttons/Button';

interface Question {
	id: number;
	question: string;
	createdAt: string;
}

const getQuestionList = async () => {
	const response = await fetch('https://pioneroroom.com/questionlist');
	const data: Question[] = await response.json();
	return data;
};

/**
 * getServerSideProps === fetch('https://...', { cache: 'no-store' });
 * getStaticProps === fetch(url)
 * getStaticPaths
 * getInitialProps
 * Incremental Static Regeneration === fetch('https://...', { next: { revalidate: 10 } });

 */

interface PageProps {
	Question: Question[];
}

const page = async ({ Question }: PageProps) => {
	const data = await getQuestionList();

	return (
		<div>
			{JSON.stringify(data)}
			<Button />
		</div>
	);
};

export default page;
