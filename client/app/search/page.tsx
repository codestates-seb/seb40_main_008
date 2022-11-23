import React from 'react'
import styles from './search.module.css';

const SearchPage = () => {
    return (
        <div className={styles.searchWrapper}>
            <div className={styles.searchboxWrapper}>
                <input
                    className={styles.searchbox}
                    placeholder='관심 클래스 찾기' />
            </div>
        </div>
    )
}

export default SearchPage;