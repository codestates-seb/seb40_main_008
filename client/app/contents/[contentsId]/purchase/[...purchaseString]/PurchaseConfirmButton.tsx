'use client';
import React from 'react';
import OrangeButton from '../../../../../components/Buttons/orangeButton';

const PurchaseConfirmButton = () => {
	const handlePurchase = () => {
		console.log('구매하기 버튼 클릭');
	};

	return <OrangeButton name="구매하기" handleClick={handlePurchase} />;
};

export default PurchaseConfirmButton;
