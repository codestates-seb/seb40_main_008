'use client';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { getCookie, getCookies } from 'cookies-next';
import ReactStars from 'react-rating-stars-component';
import { useRouter } from 'next/navigation';
import { useVerifyLoginClient } from '../../../../../utils/hooks/useVerifyLoginClient';
interface Props {
	uploadClassId: string;
}

const VideoComment = ({ uploadClassId }: Props) => {
	const userInfo = useVerifyLoginClient();

	const [comments, setComments] = useState('');
	const [rating, setRating] = useState(0);
	const router = useRouter();
	const handleSubmit = () => {
		if (!userInfo) return;
		const token = getCookie('accessToken');
		fetch(`https://pioneroroom.com/auth/uploadclass/${uploadClassId}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify({
				comments,
				starRate: rating,
			}),
		}).then((res) => {
			router.refresh();
			setComments('');
			if (res.status === 403)
				alert('자기 자신의 강의에는 댓글을 못단다구요~');
		});
	};

	return (
		<div
			style={{
				width: '100%',
				height: '80px',
				display: 'flex',
				flexDirection: 'row',
				justifyContent: 'center',
				alignItems: 'center',
			}}
		>
			<div
				style={{
					display: 'flex',
					flexDirection: 'column',
					width: '100%',
				}}
			>
				<ReactStars
					count={5}
					value={rating}
					onChange={(e: number) => setRating(e)}
					size={24}
					isHalf={false}
					activeColor="#ffd700"
				/>
				<div>
					<input
						style={{
							width: '90%',
							height: '30px',
							border: '1px solid white',
							borderRadius: '5px',
							backgroundColor: '#d9d9d926',
							color: 'white',
							paddingLeft: '10px',
							marginRight: '10px',
						}}
						type="text"
						value={comments}
						onChange={(e) => setComments(e.target.value)}
					/>
					<FontAwesomeIcon
						onClick={handleSubmit}
						size={'lg'}
						icon={faPaperPlane}
						width={24}
					/>
				</div>
			</div>
		</div>
	);
};

export default VideoComment;
