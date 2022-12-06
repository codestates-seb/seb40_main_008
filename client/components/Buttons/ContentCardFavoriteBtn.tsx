'use client';
import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import styles from '../content/ContentInfo.module.css';
import { getCookie } from 'cookies-next';
import { IContent } from '../../types/contents';
interface ContentCardFavoriteProps {
	contentInfo: IContent;
}

export const ContentCardFavoriteBtn = ({
	contentInfo,
}: ContentCardFavoriteProps) => {
	const [like, setLike] = useState(contentInfo.wished);
	const token = getCookie('accessToken');

	const postLike = async () => {
		const res = await fetch(
			`https://pioneroroom.com/auth/${contentInfo.contentsId}/likes`,
			{
				method: 'patch',
				headers: {
					Authorization: `Bearer ${token}`,
				},
			}
		);
		console.log(
			'🚀 ~ file: ContentCardFavoriteBtn.tsx:34 ~ postLike ~ res',
			res
		);
	};

	return (
		<>
			<button onClick={postLike} className={styles.zzimbtn}>
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
