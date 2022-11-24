'use client'
import React from 'react'
import { redirect } from 'next/navigation';
import styles from './SearchResultFilter.module.css'

const SearchResultFilter = ({ params }: any) => {

    const handleSortChange = (e: any) => {
        if (params.categoryName === e.target.value) return;

        redirect(`/category/sort/${e.target.value}?categoies=${params.categoryName}`)
        //http://localhost:8080/category/sort/newest?categories=PROGRAMMING
        //http://localhost:8080/category/sort/likes?categories=PROGRAMMING

    }

    return (

        <div className={styles.filterWrapper}>
            <select className={styles.selectzone} onChange={handleSortChange}>
                <option value='likes' className={styles.options}>인기순</option>
                <option value='newest' className={styles.options}>최신순</option>
            </select>
        </div>
    )
}

export default SearchResultFilter;