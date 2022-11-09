import Head from 'next/head';
import Image from 'next/image';
import styles from '../styles/Home.module.css';

export default function Home({ data }: { data: any }) {
	console.log(data);

	return <h1>Hello world</h1>;
}
