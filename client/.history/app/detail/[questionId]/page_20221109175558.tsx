import React from 'react';

export async function generateStaticParams() {
   const arr = new Array(10).fill(1).map((, i) => {id: i.toString()})
   return [{ id: '1' }, { id: '2' }]
 }
 

const DetailIdPage = ({ params }) => {
	console.log('🚀 ~ file: page.tsx ~ line 4 ~ DetailIdPage ~ params', params);
	return <div>DetailIdPage</div>;
};

export default DetailIdPage;
