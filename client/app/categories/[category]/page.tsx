import React from 'react';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
import { Content } from '../../../types/homeScreen/mainVideoContents';

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// Homeclasses Section interface type도 ICategorySearchResult로 바꾸기
// 필터링 부분 컴포넌트화 하기
// Classes 그리드 부분 맞춰서 가져다 쓰기

const getClassesContents = async (): Promise<Array<Content>> => {
    try {
        const response = await fetch('https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109', {
            next: {
                revalidate: 60,
            },
        });
        const { contentsList } = await response.json();
        return contentsList;
    } catch (error) {
        console.error(error);
        return [];
    }
};

interface HomeContentProps {
    contentList: Content[];
}

const fetchCategoryData = async (
    categoryName: string
): Promise<ICategorySearchResult[]> => {
    const res = await fetch(
        `https://pioneroroom.com/category/sort/likes?categories=${categoryName}`
    );
    const body = await res.json();
    return body;
};

const DetailCategoryPage = async ({ params }: any) => {

    const contentsList = await getClassesContents();
    const get = await fetchCategoryData(params.categoryName);

    return (
        <>
            <SearchResultFilter searchTerm={params.categoryName} />
            <div>

                {/* <p>카테고리 이름{params.categoryName}</p> */}
                {/* <p>json 정보{JSON.stringify(get)}</p> */}

                <HomeClassesSection contentList={contentsList} />

                {/* 갖가지 강의영상에 대한 정보 그리드 정보 */}

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