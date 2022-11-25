import React from 'react';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
import { Content } from '../../../types/homeScreen/mainVideoContents';

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// Homeclasses Section interface type도 ICategorySearchResult로 바꾸기
// 필터링 부분 컴포넌트화 하기
// Classes 그리드 부분 맞춰서 가져다 쓰기
// 검색어가 카테고리 arr에 포함되면 categoryName으로 아니면 검색어로 req보내기

// Grid 콘텐츠 불러오기 부분
interface HomeContentProps {
    contentList: Content[];
}

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

// categoryName 또는 검색어에 대한 get요청
const fetchCategoryData = async (
    categoryName: string
): Promise<ICategorySearchResult[]> => {
    const res = await fetch(
        // category search
        `https://pioneroroom.com/search?categories=${categoryName}`

        // search engine
        // `https://pioneroroom.com/search/title/?keyword=${searchName}`
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