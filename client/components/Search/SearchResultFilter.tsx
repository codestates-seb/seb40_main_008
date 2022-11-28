'use client';
import React from 'react';
import styles from './SearchResultFilter.module.css';
import { useRouter } from 'next/navigation';
import { AppRouterInstance } from 'next/dist/shared/lib/app-router-context';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCaretDown } from "@fortawesome/free-solid-svg-icons";

export const handleSortChange = (
    e: any,
    category: string,
    router: AppRouterInstance
) => {
    const value = e.target.value;

    router.push(`/categories/${category}-${value}`);

    // 카테고리 변경 시, redirect 주소
    //'http://localhost:8080/search?categories=MUSIC'
    //'http://localhost:8080/search?categories=MUSIC&sort=likesCount'
    //'http://localhost:8080/search?categories=MUSIC&sort=newest'

    // redirect(`/search?categoies=${category.category}&sort=${e.target.value}`)

    return value;
};

const SearchResultFilter = (params: any) => {
    const category = params.category;
    const router = useRouter();

    return (
        <div className={styles.filterWrapper}>
            <select
                name='인기순'
                className={styles.selectzone}
                onChange={(e) => handleSortChange(e, category, router)}
            >
                <option value="likesCount" className={styles.options}>
                    인기순
                </option>
                <option value="newest" className={styles.options}>
                    최신순
                </option>
                {/* <FontAwesomeIcon
                    icon={faCaretDown}
                    className={styles.faCaretDown} /> */}
            </select>
        </div>
    );
};

export default SearchResultFilter;