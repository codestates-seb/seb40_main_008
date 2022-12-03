'use client';
import { useRouter } from 'next/navigation';
import React, { useEffect } from 'react';

const AlertCheck = ({ status }: { status: string }) => {
	const router = useRouter();
	if (status === 'success') alert('코인 충전이 완료되었습니다.');
	else if (status === 'fail') alert('코인 충전이 실패하였습니다.');
	else if (status === 'cancel') alert('코인 충전이 취소되었습니다.');

	useEffect(() => {
		router.push('/mypage');
	});

	return null;
};

export default AlertCheck;
