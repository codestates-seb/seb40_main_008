'use client'
import React, { useState } from 'react';
import { redirect } from 'next/navigation';
import styles from './SearchResultFilter.module.css';
import getCategoryContents from '../../app/categories/[category]/page';

export const handleSortChange = (e: any) => {

    const value = e.target.value;

    console.log('value', value);

    // if (category.category === e.target.value) return;

    // 카테고리 변경 시, redirect 주소
    //'http://localhost:8080/search?categories=MUSIC'
    //'http://localhost:8080/search?categories=MUSIC&sort=likesCount'
    //'http://localhost:8080/search?categories=MUSIC&sort=newest'

    // redirect(`/search?categoies=${category.category}&sort=${e.target.value}`)

    return value;
}

const SearchResultFilter = (category: any) => {

    console.log('category', category.category);

    return (

        <div className={styles.filterWrapper}>
            <select className={styles.selectzone} onChange={handleSortChange}>
                <option value='likesCount' className={styles.options}>인기순</option>
                <option value='newest' className={styles.options}>최신순</option>
            </select>
        </div>
    )
}

export default SearchResultFilter;
