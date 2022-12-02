'use client';
import React from 'react';
import { getUserInfoClient } from '../../utils/helper/getUserInfoClient';
import OrangeButton from '../Buttons/orangeButton';
import { useRouter } from 'next/navigation';
import { getLastDynamicRouteId } from '../../utils/helper/getLastDynamicRouteId';

const PurchaseButton = () => {
	const router = useRouter();
	const contentId = getLastDynamicRouteId();

	const handleToPurchase = async () => {
		const userInfo = await getUserInfoClient();
		if (!userInfo) {
			router.push('/login');
			return;
		}

		router.push(`/contents/${contentId}/purchase`);
	};

	return (
		<OrangeButton handleClick={handleToPurchase} name={'강의 구매하기'} />
	);
};

export default PurchaseButton;
