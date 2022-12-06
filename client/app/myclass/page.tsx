import React from 'react';
import styles from './myclass.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import MyclassTab from '../../components/Tab/MyclassTab';
import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';
import verifyLogin from '../../utils/VerifyLogin';
import { ICategorySearchResult } from '../../types/category_search/categorySearchType';
import BaseNavbar from '../../components/BaseNavBar/BaseNavbar';

const getTakingClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;
	try {
		if (!token) {
			throw new Error('token is not defined');
		}
		const response = await fetch(`https://pioneroroom.com/auth/myclass/takingclass`, {
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
		console.log(error);
		// redirect(`/`);
		return [];
	}
};

const getWishClasses = async (): Promise<Array<ICategorySearchResult>> => {

	const token = cookies().get('accessToken')?.value;

	try {
		if (!token) {
			throw new Error('error');
		}
		const response = await fetch(`https://pioneroroom.com/auth/myclass/wishclass`, {
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
	const isLogin = await verifyLogin();

	if (!isLogin) redirect(`/login`);

	return (
		<>
			<BaseNavbar page={'back'} />
			<div className={styles.myclassWrapper}>
				<h1 className={styles.title}>내 클래스</h1>
				<MyclassTab takingClasses={takingClasses} wishClasses={wishClasses} />
			</div>
			<TabNavigator activeLink={'myclass'} />
		</>
	);
};

export default MyclassPage;
