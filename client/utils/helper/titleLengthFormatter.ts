export const titleLengthFormatter = (title: string) => {
	if (title.length > 25) {
		return `${title.slice(0, 20)}...`;
	}
	return title;
};
