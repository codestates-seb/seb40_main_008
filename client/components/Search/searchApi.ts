export const searchApi = {
	getSearch: async (value: string) => {
		try {
			const res = await fetch(`https://pioneroroom.com/search/title?keyword=${encodeURIComponent(value)}`,
			);
			const { contentsList } = await res.json();
			console.log("🚀 ~ file: searchApi.ts:7 ~ getSearch: ~ contentsList", contentsList)
			return contentsList;
		}
		catch (error) {
			console.error(error);
		}
	},
};

// String query = URLEncoder.encode("스포츠", "UTF-8");
// ${encodeURIComponent('한글파라미터')