import { headers } from 'next/headers';
import React, { use } from 'react';
import { getSession } from '../../../utils/helper/session';

export async function generateStaticParams() {
	const res = await fetch('https://pioneroroom.com/questionlist');
	const data = await res.json();
	const arr = data.data.map((e: any) => {
		console.log('map', e.questionId);
		return {
			questionId: String(e.questionId),
		};
	});
	return arr;
}

const fetchPost = async (id: any) => {
	const res = await fetch(`https://pioneroroom.com/questionlist/${id}`);
	return await res.json().then((res) => res.data);
};

const DetailIdPage = async ({ params }: any) => {
	console.log('params.questionId', params.questionId);
	const post = await fetchPost(params.questionId);
	return (
		<div>
			<p>{JSON.stringify(post)}</p>
		</div>
	);
};

export default DetailIdPage;
