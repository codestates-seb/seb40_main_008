'use client';
import React from 'react';
import { VerifyLoginClient } from '../../utils/helper/VerifyLoginClient';
import OrangeButton from '../Buttons/orangeButton';
import { useRouter } from 'next/navigation';
import { getLastDynamicRouteId } from '../../utils/helper/getLastDynamicRouteId';
import { IContent } from '../../types/contents';

interface Props {
	contentInfo: IContent;
	contentId: number;
}

const PurchaseButton = ({ contentInfo, contentId }: Props) => {
	const router = useRouter();
	const queryString = `?contentId=${contentId}&price=${contentInfo?.price}&title=${contentInfo?.title}&thumbnail=${contentInfo?.thumbnail}&tutorName=${contentInfo?.tutorName}`;

	const handleToPurchase = async () => {
		const userInfo = await VerifyLoginClient();
		if (!userInfo) {
			router.push('/login');
			return;
		}
		router.push(`/contents/${contentId}/purchase/purchase${queryString}`);
	};

	router.prefetch('/login');
	router.prefetch(`/contents/${contentId}/purchase/purchase${queryString}`);

	return (
		<OrangeButton handleClick={handleToPurchase} name={'강의 구매하기'} />
	);
};

export default PurchaseButton;
