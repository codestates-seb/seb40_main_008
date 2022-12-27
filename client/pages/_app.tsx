import '../styles/globals.css';
import type { AppProps } from 'next/app';
import { useEffect } from 'react';

export default function App({ Component, pageProps }: AppProps) {
	useEffect(() => {
		if ('serviceWorker' in navigator) {
			const regisInit = async () => {
				// const registration = await navigator.serviceWorker.register('/service-worker.js');
				const registration = await navigator.serviceWorker.register(
					'/sw.js'
				);
				registration.waiting?.postMessage({ type: 'SKIP_WAITING' });

				regisInit();
			};
		}
	}, []);
	console.log('asdf');

	return <Component {...pageProps} />;
}
