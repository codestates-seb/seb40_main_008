import Head from 'next/head';
import Image from 'next/image';
import styles from '../styles/Home.module.css';

export async function getServerSideProps() {
	const res = await fetch('https://pioneroroom.com/questionlist');

	return {
		props: {
			data: await res.json(),
		},
	};
}

export default function Home({ data }: { data: any }) {
	console.log(data);

	return <h1>Hello world</h1>;
}
