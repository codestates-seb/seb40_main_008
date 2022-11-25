"use client";
import React, { use, useState } from 'react'
import styles from './search.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import getSearchData from '../../components/Search/PopularKeyword';
import { searchApi } from '../../components/Search/searchApi';

// client 페이지
// input에 value 에 추가
// params.categoryName으로 받기

const SearchPage = () => {

    // 인기 검색어(무한 fetching 문제 발생...)
    // const popularKeyword = use(getSearchData());

    // 검색어 
    const [searchvalue, setSearchvalue] = useState('');

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setSearchvalue(e.target.value);
        console.log(searchvalue);

    }

    // 검색 버튼 클릭 시, 내용 담아서 GET 요청 보내기
    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        // console.log(searchvalue);
        // alert(JSON.stringify(searchvalue));
        searchApi.getSearch(searchvalue);
    }

    return (
        <>
            <div className={styles.searchpageWrapper}>
                <div className={styles.searchWrapper}>
                    <div className={styles.searchboxWrapper}>
                        <form onSubmit={handleSubmit}>
                            <input
                                value={searchvalue}
                                onChange={handleChange}
                                className={styles.searchbox}
                                placeholder='관심 클래스 찾기' />
                            <button className={styles.submitbtn} type='submit'>
                                <FontAwesomeIcon
                                    icon={faMagnifyingGlass}
                                    className={styles.magnifyingglass} />
                            </button>
                        </form>
                    </div>
                </div>

                <div className={styles.title}>인기순</div>

                {/* {popularKeyword.map((e: any, idx: number) => {
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
            <TabNavigator activeLink={'home'} />
        </>
    )
}

export default SearchPage;