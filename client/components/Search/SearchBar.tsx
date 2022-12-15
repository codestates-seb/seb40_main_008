'use client';
import React, { useState } from 'react';
import { searchApi } from './searchApi';
import styles from './SearchBar.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { useRouter } from 'next/navigation';

const SearchBar = () => {
	// 검색어
	const [searchvalue, setSearchvalue] = useState('');
	const router = useRouter();
	const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSearchvalue(e.target.value);
	};

	// 검색 버튼 클릭 시, 내용 담아서 GET 요청 보내기
	const handleSubmit = async (e: React.MouseEvent<HTMLElement>) => {
		e.preventDefault();
		router.push(`/categories/${searchvalue}`);
	};

	return (
		<>
			<div className={styles.searchWrapper}>
				<div className={styles.searchboxWrapper}>
					<div>
						<input
							value={searchvalue}
							onChange={handleChange}
							className={styles.searchbox}
							placeholder="관심 클래스 찾기"
						/>
						<button className={styles.submitbtn} onClick={handleSubmit}>
							<FontAwesomeIcon
								width={24}
								icon={faMagnifyingGlass}
							// className={styles.magnifyingglass}
							/>
						</button>
					</div>
				</div>
			</div>
		</>
	);
};

export default SearchBar;
