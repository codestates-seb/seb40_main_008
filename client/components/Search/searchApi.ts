export const searchApi = {
    getSearch: async (value: string) => {
        try {
            const res = await fetch(`https://pioneroroom.com/search/title?keyword=${value}`);
            console.log(res);
            return res;
        }
        catch (error) {
            console.error(error);
        }
    },
};