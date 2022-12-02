import React from 'react';

export const useHasWindow = () => {
	const [isWindow, setIsWindow] = React.useState(false);

	React.useEffect(() => {
		setIsWindow(true);
	}, []);

	return isWindow;
};
