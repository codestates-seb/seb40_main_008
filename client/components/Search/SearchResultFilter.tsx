'use client'
import React from 'react'
import { redirect } from 'next/navigation';
import styles from './SearchResultFilter.module.css'

const SearchResultFilter = ({ params }: any) => {

    const handleSortChange = (e: any) => {
        if (params.categoryName === e.target.value) return;

        // 카테고리 변경 시, redirect 주소
        //'http://localhost:8080/search?categories=MUSIC'
        //'http://localhost:8080/search?categories=MUSIC&sort=likesCount'
        //'http://localhost:8080/search?categories=MUSIC&sort=newest'
        redirect(`/search?categoies=${params.categoryName}&sort=${e.target.value}`)
    }

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