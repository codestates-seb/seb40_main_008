import React from 'react';
import CategoryNavBar from '../../../components/CategoryNavBar/CategoryNavBar';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
import styles from './categorydetail.module.css';

// geneticStaticParams 도 만들기,,,(detail,,,에 있는 것과 같이)
// 검색어가 카테고리 arr에 포함되면 categoryName으로 아니면 검색어로 req보내기

const getCategoryContents = async (
	category: string,
	sortingMethod: string
): Promise<Array<ICategorySearchResult>> => {
	try {
		// https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818
		// request url : https://pioneroroom.com/search?categories=${category}&sort=${sortingMethod}
		const response = await fetch(
			`https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818`,
			{
				next: {
					revalidate: 60,
				},
			}
		);
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const DetailCategoryPage = async (context: any) => {
	/*
      params: 'DRAWING_CONTENT?name=likesCount || newest '
      params: 'DRAWING_CONTENT'
     */

	const { params } = context;
	const { category } = params;

	let contentsList;

	if (category.includes('-')) {
		const [categoryName, value] = category.split('-');
		contentsList = await getCategoryContents(categoryName, value);
	} else {
		contentsList = await getCategoryContents(category, 'likesCount');
	}

	return (
		<>
			<CategoryNavBar />
			<div className={styles.filterWrapper}>
				<SearchResultFilter category={category} />
			</div>
			<div>
				<HomeClassesSection contentsList={contentsList} />
			</div>
		</>
	);
};

export default DetailCategoryPage;
