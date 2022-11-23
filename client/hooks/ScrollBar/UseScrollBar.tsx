import { useEffect, useState } from 'react';
import { useTrottle } from './useTrottle';

export const UseScrollBar = () => {
	const [show, setShow] = useState(true);
	const [lastScrollY, setLastScrollY] = useState(0);

	const controlNavbar = () => {
		if (typeof window !== 'undefined') {
			if (window.scrollY > lastScrollY) {
				// if scroll down hide the navbar
				setShow(false);
			} else {
				// if scroll up show the navbar
				setShow(true);
			}
			// remember current page location to use in the next move
			setLastScrollY(window.scrollY);
		}
	};
	const trottleNavbarControl = useTrottle(controlNavbar, 200);

	useEffect(() => {
		if (typeof window !== 'undefined') {
			window.addEventListener('scroll', trottleNavbarControl);

			// cleanup function
			return () => {
				window.removeEventListener('scroll', trottleNavbarControl);
			};
		}
	}, [lastScrollY, trottleNavbarControl]);

	return { show };
};
