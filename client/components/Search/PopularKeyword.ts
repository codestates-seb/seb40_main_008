// 인기 검색어 get 함수

const getSearchData = async () => {

    try {
        const response = await fetch(`http://localhost:3000/api/popularkeyword`);
        const { data } = await response.json();
        return data;

    } catch (error) {
        console.error(error);
        return [];
    }
}

export default getSearchData;