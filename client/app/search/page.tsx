import React from 'react';
import styles from './search.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import SearchBar from '../../components/Search/SearchBar';
import getSearchData from '../../components/Search/PopularKeyword';

// client 페이지
// input에 value 에 추가
// params.categoryName으로 받기

const SearchPage = async () => {
    // 인기 검색어(무한 fetching 문제 발생...)
    // const popularKeyword = await getSearchData();

    return (
        <>
            <div className={styles.searchpageWrapper}>
                <SearchBar />

                {/* <div className={styles.title}>인기순</div>

                {popularKeyword.map((e: any, idx: number) => {
                    return (
                        <ol>
                            <li className={styles.li}>
                                <span className={styles.num}>{idx + 1}</span>
                                <span>{e.title}</span>
                            </li>
                        </ol>
                    )
                })} */}
            </div>
            <TabNavigator activeLink={'search'} />
        </>
    );
};

export default SearchPage;
