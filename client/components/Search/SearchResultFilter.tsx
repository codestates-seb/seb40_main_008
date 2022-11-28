'use client';
import React, { useState } from 'react';
import { redirect } from 'next/navigation';
import styles from './SearchResultFilter.module.css';
import getCategoryContents from '../../app/categories/[category]/page';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { AppRouterInstance } from 'next/dist/shared/lib/app-router-context';

export const handleSortChange = (
	e: any,
	category: string,
	router: AppRouterInstance
) => {
	const value = e.target.value;

	console.log('value', value);
	router.push(`/categories/${category}-${value}`);
	// redirect('http://localhost:3000');
	// router.push('http://localhost:3000');
	/**
	 * value: likesCount, newest
	 */
	// if (category.category === e.target.value) return;

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
			<button onClick={() => router.push('/')}>home</button>
			<select
				className={styles.selectzone}
				onChange={(e) => handleSortChange(e, category, router)}
			>
				<option value="likesCount" className={styles.options}>
					인기순
				</option>
				<option value="newest" className={styles.options}>
					최신순
				</option>
			</select>
		</div>
	);
};

export default SearchResultFilter;
