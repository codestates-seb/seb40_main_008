import React from 'react'
import styles from './SearchResultFilter.module.css'

const SearchResultFilter = ({ params }: any) => {

    // const handleSortChange = (e: any) => {
    //     if (현재필터링조건 === e.target.value) return;
    //     redirect(/category/sort / { e.target.value } / categoies={ searchTerm })
    // }

    return (

        // 필터링에 관한 정보. 
        // <div>
        // <input onChange={handleSortChange} />
        // </div>

        <div className={styles.filterWrapper}>
            <select className={styles.selectzone}>
                <option className={styles.options}>인기순</option>
                <option className={styles.options}>최신순</option>
            </select>
        </div>
    )
}

export default SearchResultFilter;