// 'use client'
import React from 'react';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
// import { useRouter } from 'next/navigation'

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// Homeclasses Section interface type도 ICategorySearchResult로 바꾸기
// 필터링 부분 컴포넌트화 하기
// Classes 그리드 부분 맞춰서 가져다 쓰기
// 검색어가 카테고리 arr에 포함되면 categoryName으로 아니면 검색어로 req보내기

// 백엔드 질문: 로그인 시 토큰 '쿠키'에 담아서 줘야하는지? / 헤더에 담아서 주면 안되는지?

// categoryName 또는 검색어에 대한 get요청(parameter에 categoryName: string 넣어야 함.)
const getCategoryContents = async (categoryName: string): Promise<ICategorySearchResult[]> => {
    try {
        //'https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109'
        // request url : https://pioneroroom.com/search?categories=${categoryName}
        const response = await fetch(`https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109`, {
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

const DetailCategoryPage = async ({ params }: any) => {

    // dynamic parameter 얻기
    // const router = useRouter();
    // console.log(router);

    const contentsList = await getCategoryContents(params.categoryName);
    console.log()

    return (
        <>
            <SearchResultFilter categoryName={params.categoryName} />
            <div>

                <HomeClassesSection contentsList={contentsList} />

                {/* 갖가지 강의영상에 대한 정보 그리드 정보 */}

            </div>
        </>
    );
};

export default DetailCategoryPage;