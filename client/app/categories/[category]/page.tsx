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
		const response = await fetch(
			`https://pioneroroom.com/search?categories=${category}&sort=${sortingMethod}`,
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
		const response = await fetch(
			sortCondition
				? `http://pioneroroom.com/search/title?keyword=${searchVal}`
				: `http://pioneroroom.com/search/title/lateast?keyword=${searchVal}`
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
			contentsList = await getCategoryContents(category, 'popular');
		}
	}

	return (
		<>
			<CategoryNavBar name={category} />
			<div className={styles.filterWrapper}>
				<SearchResultFilter category={category} />
			</div>
			<div className={styles.categorydetailWrapper}>
				{contentsList.length === 0 ? (
					<div className={styles.noclassWrapper}>해당하는 강좌가 없습니다.</div>
				) : (
					<HomeClassesSection contentsList={contentsList} />
				)}
			</div>
		</>
	);
};

export default DetailCategoryPage;

// export async function generateStaticParams() {
//   return fixedCategoriesEng.map((e) => {
//     return {
//       category: e,
//     };
//   });
// }
