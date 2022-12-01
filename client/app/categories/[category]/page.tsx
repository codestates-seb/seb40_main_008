import React from 'react';
import CategoryNavBar from '../../../components/CategoryNavBar/CategoryNavBar';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import SearchResultFilter from '../../../components/Search/SearchResultFilter';
import HomeClassesSection from '../../../components/Card/HomeClassesSection';
import styles from './categorydetail.module.css';
import { fixedCategoriesEng } from '../../../constants/fixedCategorys';

const getCategoryContents = async (
	category: string,
	sortingMethod: string
): Promise<Array<ICategorySearchResult>> => {
	try {
		// https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818
		// request url : https://pioneroroom.com/search?categories=${category}&sort=${sortingMethod}
		const response = await fetch(
			`https://run.mocky.io/v3/072e5b64-e3fb-4b38-category-313b8b680818`,
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

const getSearchContents = async (searchVal: string, sortingMethod: string) => {
	const sortCondition = sortingMethod === '' || sortingMethod === 'newest';
	try {
		// https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818
		// request url : https://pioneroroom.com/search?categories=${category}&sort=${sortingMethod}
		const response = await fetch(
			sortCondition
				? `http://pioneroroom/search/title/lateast?keyword=${searchVal}`
				: `http://pioneroroom/search/title?keyword=${searchVal}`
		);
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const DetailCategoryPage = async ({ params: { category } }: any) => {
	let contentsList;
	const [categoryName, sortingMethod] = category.includes('-')
		? category.split('-')
		: [category, ''];

	if (!fixedCategoriesEng.includes(categoryName)) {
		contentsList = await getSearchContents(categoryName, sortingMethod);
	} else {
		if (category.includes('-')) {
			contentsList = await getCategoryContents(categoryName, sortingMethod);
		} else {
			contentsList = await getCategoryContents(category, 'likesCount');
		}
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
