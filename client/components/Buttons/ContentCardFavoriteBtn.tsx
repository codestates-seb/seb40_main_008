'use client';
import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart, faSpinner } from '@fortawesome/free-solid-svg-icons';
import styles from '../content/ContentInfo.module.css';
import { getCookie } from 'cookies-next';
import { IContent } from '../../types/contents';
import { useRouter } from 'next/navigation';
import catchfetch from '../../catchfetch';
interface ContentCardFavoriteProps {
	contentInfo: IContent;
}

export const ContentCardFavoriteBtn = ({
	contentInfo,
}: ContentCardFavoriteProps) => {
	const [like, setLike] = useState(contentInfo.liked);
	const [isLoading, setIsLoading] = useState(false);
	const token = getCookie('accessToken');
	const router = useRouter();

	useEffect(() => {
		setLike(contentInfo.wished);
		setIsLoading(false);
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [contentInfo.liked]);

	const handlePost = () => {
		async function postLike() {
			setIsLoading(true);
			try {
				const res = await catchfetch(
					`https://pioneroroom.com/auth/${contentInfo.contentsId}/likes`,
					{
						method: 'POST',
						headers: {
							Authorization: `Bearer ${token}`,
						},
						body: JSON.stringify({
							liked: !like,
						}),
					}
				);
				console.log("🚀 ~ file: ContentCardFavoriteBtn.tsx:43 ~ postLike ~ res", res)
				if (res.ok) {
					router.refresh();
				} else {
					throw new Error('요청 실패하였습니다.');
				}
			} catch (err) {
				console.error(err);
			}
		}

		postLike();
		if (!like) return;

		const confirmRes = confirm('좋아요를 취소하시겠습니다?');
		if (confirmRes) {
			postLike();
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
					icon={faHeart}
					width={24}
					color={like ? 'red' : 'white'}
					className={`${like ? styles.icon : styles.clickicon}`}
				/>
				좋아요
			</button>
		</>
	);
};
