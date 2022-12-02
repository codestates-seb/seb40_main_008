'use client';
import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { getCookie, getCookies } from 'cookies-next';

interface Props {
	uploadClassId: string;
}

const VideoComment = ({ uploadClassId }: Props) => {
	const [comments, setComments] = useState('');

	const handleSubmit = () => {
		const token = getCookie('accessToken');
		fetch(`https://pioneroroom.com/auth/uploadclass/${uploadClassId}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
			body: JSON.stringify({
				comments,
				starRate: 5,
			}),
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
			<input
				style={{
					width: '80%',
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
	);
};

export default VideoComment;
