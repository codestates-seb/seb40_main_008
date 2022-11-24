import React from 'react'
import styles from './search.module.css';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import TabNavigator from '../../components/TabNavigator/TabNavigator';

const getSearchData = async () => {
    const res = await fetch('https://jsonplaceholder.typicode.com/todos?_start=0&_end=10');

    if (!res.ok) {
        throw new Error('failed to data fetch')
    }
    return res.json();
}

const SearchPage = async () => {

    const searchData = await getSearchData();

    console.log(searchData);

    return (
        <>
            <div className={styles.searchpageWrapper}>
                <div className={styles.searchWrapper}>
                    <div className={styles.searchboxWrapper}>
                        <input
                            className={styles.searchbox}
                            placeholder='관심 클래스 찾기' />
                        <FontAwesomeIcon
                            icon={faMagnifyingGlass}
                            className={styles.magnifyingglass} />
                    </div>
                </div>

                <div className={styles.title}>인기순</div>

                {searchData.map((e: any, idx: number) => {
                    return (
                        <li className={styles.li}>{idx}.{e.title}</li>
                    )
                })}

            </div>
            <TabNavigator activeLink={'home'} />
        </>
    )
}

export default SearchPage;