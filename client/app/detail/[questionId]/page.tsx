import { headers } from 'next/headers';
import React, { use } from 'react';
import { getSession } from '../../../utils/helper/session';

const fetchPost = async (id: any) => {
	const res = await fetch(`https://pioneroroom.com/questionlist/${id}`);
	return await res.json().then((res) => res.data);
};

const DetailIdPage = async ({ params }: any) => {
	const post = await fetchPost(params.questionId);
	return (
		<div>
			<p>{JSON.stringify(post)}</p>
		</div>
	);
};

// BUG: generateStaticParams 함수가 현재 dev 모드에서 동작하지 않음.
// dynamic headers( next/headers )의 cookie등을 불러올 때 오류를 일으키고,
// dev mode에서 이 함수와 결합하여 사용하면 dynamic server usage: headers error 발생함.
/*
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
*/

export default DetailIdPage;
