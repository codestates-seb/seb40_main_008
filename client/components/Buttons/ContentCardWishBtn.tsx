'use client';
import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCartShopping, faSpinner } from '@fortawesome/free-solid-svg-icons';
import styles from '../content/ContentInfo.module.css';
import { getCookie } from 'cookies-next';
import { IContent } from '../../types/contents';
import { useRouter } from 'next/navigation';
interface ContentCardWishProps {
	contentInfo: IContent;
}

export const ContentCardWishBtn = ({ contentInfo }: ContentCardWishProps) => {
	const token = getCookie('accessToken');
	const [wish, setWish] = useState(contentInfo.wished);
	const [isLoading, setIsLoading] = useState(false);

	const router = useRouter();

	useEffect(() => {
		setWish(contentInfo.wished);
		setIsLoading(false);
	}, [contentInfo.wished]);

	const handlePost = () => {
		async function postWish() {
			setIsLoading(true);
			const res = await fetch(
				`https://pioneroroom.com/auth/${contentInfo.contentsId}/wish`,
				{
					method: 'POST',
					headers: {
						Authorization: `Bearer ${token}`,
					},
				}
			);
			if (res.ok) {
				router.refresh();
			}
		}
		postWish();
		if (!wish) return;

		const confirmRes = confirm('장바구니에서 삭제하시겠습니까?');
		if (confirmRes) {
			postWish();
		}
	};

	if (isLoading) {
		return (
			<FontAwesomeIcon
				width={24}
				icon={faSpinner}
				color={'white'}
				className="spinner"
			/>
		);
	}

	return (
		<>
			<button onClick={handlePost} className={styles.zzimbtn}>
				<FontAwesomeIcon
					width={24}
					icon={faCartShopping}
					color={wish ? 'red' : 'white'}
				/>
			</button>
		</>
	);
};
