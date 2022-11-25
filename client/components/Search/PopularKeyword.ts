// 인기 검색어 get 함수

const getSearchData = async () => {
    const res = await fetch('http://localhost:3000/api/popularkeyword');

    if (!res.ok) {
        throw new Error('failed to data fetch')
    }

    return await res.json();
}

export default getSearchData;