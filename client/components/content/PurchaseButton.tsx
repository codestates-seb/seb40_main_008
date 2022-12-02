'use client';
import { useRouter } from 'next/router';
import React from 'react';
import { getUserInfoClient } from '../../utils/helper/getUserInfoClient';
import OrangeButton from '../Buttons/orangeButton';

const PurchaseButton = () => {
	const router = useRouter();
	const query = router.query;
	console.log(
		'🚀 ~ file: PurchaseButton.tsx:11 ~ PurchaseButton ~ query',
		query
	);

	const handleToPurchase = async () => {
		const userInfo = await getUserInfoClient();
		if (!userInfo) router.push('/login');

		router.push('/contents/[contentsId]/purchase');
	};

	return (
		<OrangeButton handleClick={handleToPurchase} name={'강의 구매하기'} />
	);
};

export default PurchaseButton;
