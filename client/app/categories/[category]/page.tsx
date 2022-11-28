import React from 'react';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
import { handleSortChange } from '../../../components/Search/SearchResultFilter';

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// Homeclasses Section interface type도 ICategorySearchResult로 바꾸기 -완
// 필터링 부분 컴포넌트화 하기 -완
// Classes 그리드 부분 맞춰서 가져다 쓰기 -완
// 검색어가 카테고리 arr에 포함되면 categoryName으로 아니면 검색어로 req보내기

// 백엔드 질문: 로그인 시 토큰 '쿠키'에 담아서 줘야하는지? / 헤더에 담아서 주면 안되는지?

// categoryName 또는 검색어에 대한 get요청(parameter에 categoryName: string 넣어야 함.)
// { params }: any
const getCategoryContents = async (
    category: string,
    sortingMethod: string
): Promise<Array<ICategorySearchResult>> => {
    try {
        // https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818

        // 'http://localhost:8080/search?categories=MUSIC&sort=likesCount'
        // request url : https://pioneroroom.com/search?categories=${category}&sort=${sortingMethod}
        const response = await fetch(
            `https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818`,
            {
                next: {
                    revalidate: 60,
                },
            }
        );

        const { contentsList } = await response.json();
        // console.log('contentsList', contentsList);
        return contentsList;
    } catch (error) {
        console.error(error);
        return [];
    }
};

const DetailCategoryPage = async (context: any) => {
    // console.log('context', context);

    // 인기순, 최신순 선택하면 각 option의 value에 맞게 다른 url parameter로 get 요청을 보내야 한다.
    // const value = handleSortChange();
    // console.log('value', value);

    const { params } = context;
    /**
     * params: 'DRAWING_CONTENT?name=likesCount || newest '
     * params: 'DRAWING_CONTENT'
     *
     */
    const { category } = params;
    console.log(
        '🚀 ~ file: page.tsx ~ line 58 ~ DetailCategoryPage ~ category',
        category
    );
    let contentsList;
    if (category.includes('-')) {
        const [categoryName, value] = category.split('-');
        contentsList = await getCategoryContents(categoryName, value);
    } else {
        contentsList = await getCategoryContents(category, 'likesCount');
    }
    // categories/DRAWING_CONTENT

    // params { category: 'DIGITAL_DRAWING' }
    // category DIGITAL_DRAWING

    // console.log('contentsList', contentsList)

    return (
        <>
            <SearchResultFilter category={category} />
            <div>
                <HomeClassesSection contentsList={contentsList} />
            </div>
        </>
    );
};

export default DetailCategoryPage;
