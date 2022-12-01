import React from 'react';
import styles from './myclass.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import { ICategorySearchResult } from '../../types/homeScreen/mainVideoContents';
import MyclassTab from '../../components/Tab/MyclassTab';
import getUserInfo from '../../utils/helper/backendUserInfo';
import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';

const getTakingClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;
	try {
		if (!token) {
			throw new Error('error');
		}
		const response = await fetch(`https://run.mocky.io/v3/a63e1d37-5ef1-4f49-ba19-d8e65cd8d7c7`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});
		const { payments } = await response.json();
		const paymentsArray = payments.map((e: any) => {
			return e.contents;
		})
		return paymentsArray;
	} catch (error) {
		console.error(error);
		// redirect(`/`);
		return [];
	}
};

const getWishClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;

	try {
		const response = await fetch(`https://run.mocky.io/v3/58bcfdf3-cafe-42d2-a460-0725cee3d02f`, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});
		const { wishes } = await response.json();
		const wishArray = wishes.map((e: any) => {
			return e.contents;
		})
		return wishArray;
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
