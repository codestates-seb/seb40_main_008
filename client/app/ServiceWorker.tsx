'use client';
import React, { useEffect } from 'react';

const ServiceWorker = () => {
	// useEffect(() => {
	// 	console.log('useeffect');
	// 	console.log('navi', navigator);
	// 	console.log('navigator', navigator);
	// 	if ('serviceWorker' in navigator) {
	// 		window.addEventListener('load', function () {
	// 			navigator.serviceWorker.register('/sw.js').then(
	// 				function (registration) {
	// 					console.log(
	// 						'Service Worker registration successful with scope: ',
	// 						registration.scope
	// 					);
	// 				},
	// 				function (err) {
	// 					console.log('Service Worker registration failed: ', err);
	// 				}
	// 			);
	// 		});
	// 	}
	// }, []);
	// useEffect(() => {
	// 	setTimeout(() => {
	// 		if (!navigator.serviceWorker) return;
	// 		navigator.serviceWorker.register('/sw.js').then(
	// 			function (registration) {
	// 				console.log(
	// 					'Service Worker registration successful with scope: ',
	// 					registration.scope
	// 				);
	// 			},
	// 			function (err) {
	// 				console.log('Service Worker registration failed: ', err);
	// 			}
	// 		);
	// 	}, 1000);
	// });
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

	console.log('app');

	return null;
};

export default ServiceWorker;
