export async function generateStaticParams() {
	const arr = new Array(10).fill(1).map((e, i) => {
		return { questionId: i.toString() };
	});
	return arr;
}

async function fetchPost(id: any) {
	const res = await fetch(`https://pioneroroom.com/questionlist/${id}`, {});
	const data = await res.json();

	return data;
}

async function dummy(id: any) {
	const res = await new Promise((resolve) => resolve(JSON.stringify('dummy')));
	return res;
}

const DetailIdPage = async ({ params }: any) => {
	const post = await dummy(params.questionId);
	return <div>{JSON.stringify(post)}</div>;
};

export default DetailIdPage;
