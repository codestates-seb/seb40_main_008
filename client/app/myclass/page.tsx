import React from 'react';
import styles from './myclass.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import { ICategorySearchResult } from '../../types/homeScreen/mainVideoContents';
import HomeClassesSection from '../../components/Card/HomeClassesSection';
import MyclassTab from '../../components/Tab/MyclassTab';
const URL = 'https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818';
const getTakingClasses = async (): Promise<Array<ICategorySearchResult>> => {
	try {
		// request url : https://pioneroroom.com/auth/takingclass
		// request url : https://pioneroroom.com/auth/wishclass
		const response = await fetch(URL);
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const getWishClasses = async (): Promise<Array<ICategorySearchResult>> => {
	try {
		const response = await fetch(URL);
		const { contentsList } = await response.json();
		return contentsList.reverse();
	} catch (error) {
		console.error(error);
		return [];
	}
};

const MyclassPage = async () => {
	const takingClasses = await getTakingClasses();
	const wishClasses = await getWishClasses();
	return (
		<>
			<div className={styles.title}>내 클래스</div>
			<MyclassTab takingClasses={takingClasses} wishClasses={wishClasses} />

			<TabNavigator activeLink={'myclass'} />
		</>
	);
};

export default MyclassPage;
