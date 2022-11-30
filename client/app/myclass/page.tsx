import React from 'react';
import styles from './myclass.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import { ICategorySearchResult } from '../../types/homeScreen/mainVideoContents';
import MyclassTab from '../../components/Tab/MyclassTab';
import getUserInfo from '../../utils/helper/backendUserInfo';
import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';
// const URL = 'https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818';

const getTakingClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;

	try {
		const response = await fetch(`https://pioneroroom.com/auth/myclass/takingclass`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const getWishClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;

	try {
		const response = await fetch(`https://pioneroroom.com/auth/myclass/wishclass`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const MyclassPage = async () => {
	const takingClasses = await getTakingClasses();
	const wishClasses = await getWishClasses();

	const userInfo = await getUserInfo(cookies().get('accessToken')?.value ?? '');

	return (
		<>
			{userInfo ?
				(
					<>
						<div className={styles.title}>내 클래스</div>
						<MyclassTab takingClasses={takingClasses} wishClasses={wishClasses} />

						<TabNavigator activeLink={'myclass'} />
					</>
				)
				:
				redirect(`/login`)
			}
		</>

	);
};

export default MyclassPage;
