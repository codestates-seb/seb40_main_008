import React from 'react';

const fetchGet = async (category: any) => {
	const res = await fetch(`https://pioneroroom.com/contentsList/${category}`);
	return await res.json().then((res) => res.data);
};

const DetailCategoryPage = async ({ params }: any) => {
	const get = await fetchGet(params.categoryId);
	return (
		<div>
			<p>{JSON.stringify(get)}</p>
		</div>
	);
};

export default DetailCategoryPage;

// 카테고리 필터링 작업

// 1. categoryId별로 필터링된 api에서 가져오면 될지?(카테고리/최신순/인기순)
// 2. 전체 카테고리 리스트를 주시면 프론트에서 필터링..?
// 3. 카테고리 네이밍 BE에서 정한게 있는지?
// --> 백엔드에 쿼리스트링으로 넘겨주기(스티랑 값/URL 받으면 이어 작업하기)
