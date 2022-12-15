import { useCallback, useRef } from 'react';

// create a trottle customhook  wrapper for the scroll event type safe
export function useTrottle<T extends (...args: any) => void>(callback: T, delay: number) {
	const lastCall = useRef(0);
	return useCallback(
		(...args: Parameters<T>) => {
			const now = new Date().getTime();
			if (now - lastCall.current < delay) {
				return;
			}
			lastCall.current = now;
			return callback(...args);
		},
		[callback, delay]
	);
}
