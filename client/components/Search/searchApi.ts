export const searchApi = {
    getSearch: async (value: string) => {
        try {
            const res = await fetch(`https://pioneroroom.com/search/title?keyword=${value}`);
            console.log(res);
            return res.json().then((res) => res.data);
        }
        catch (error) {
            console.error(error);
        }
    },
};