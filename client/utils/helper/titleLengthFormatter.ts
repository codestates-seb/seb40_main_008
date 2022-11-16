export const titleLengthFormatter = (title: string) => {
	if (title.length > 30) {
		return `${title.slice(0, 30)}...`;
	}
	return title;
};
