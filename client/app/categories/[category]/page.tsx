import React from 'react';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// Homeclasses Section interface type도 ICategorySearchResult로 바꾸기
// 필터링 부분 컴포넌트화 하기
// Classes 그리드 부분 맞춰서 가져다 쓰기

const fetchCategoryData = async (
    categoryName: string
): Promise<ICategorySearchResult[]> => {
    // const res = await fetch(https://pioneroroom.com/contentsList/${category});
    const res = await fetch(
        `https://pioneroroom.com/category/sort/likes?categories=${categoryName}`
    );
    const body = await res.json();
    return body;
};

const DetailCategoryPage = async ({ params }: any) => {

    const get = await fetchCategoryData(params.categoryName);

    return (
        <>
            <SearchResultFilter searchTerm={params.categoryName} />
            <div>


                {/* 갖가지 강의영상에 대한 정보 그리드 정보 */}


                {/* <p>{JSON.stringify(get)}</p> */}
            </div>
        </>
    );
};

export default DetailCategoryPage;

// 카테고리 필터링 작업

// 1. categoryId별로 필터링된 api에서 가져오면 될지?(카테고리/최신순/인기순)
// 2. 전체 카테고리 리스트를 주시면 프론트에서 필터링..?
// 3. 카테고리 네이밍 BE에서 정한게 있는지?
// --> 백엔드에 쿼리스트링으로 넘겨주기(스티랑 값/URL 받으면 이어 작업하기)