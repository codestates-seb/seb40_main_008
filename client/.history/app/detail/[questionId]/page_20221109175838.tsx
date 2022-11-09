import React from 'react';

export async function generateStaticParams() {
	const arr = new Array(10).fill(1).map((e, i) => {
		return { id: i.toString() };
	});
	return arr;
}

async function fetchPost(id) {
	const res = await fetch(`https://pioneroroom.com/questionlist/${id}`);
	const data = await res.json();

	return data;
}

const DetailIdPage = async ({ params }) => {
	const post = await fetchPost(params.id);

	return <div>{JSON.stringify(post)}</div>;
};

export default DetailIdPage;
