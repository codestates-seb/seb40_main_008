export const searchApi = {
    getSearch: async (value: string) => {
        try {
            // mock dataurl : https://run.mocky.io/v3/15b99084-df18-4621-8548-da1deff8cad2
            // req url : https://pioneroroom.com/search/title?keyword=${value}
            const res = await fetch(`https://pioneroroom.com/search/title?keyword=${value}`);
            const { contentsList } = await res.json();
            console.log("🚀 ~ file: searchApi.ts:7 ~ getSearch: ~ contentsList", contentsList)
            return contentsList;
        }
        catch (error) {
            console.error(error);
        }
    },
};