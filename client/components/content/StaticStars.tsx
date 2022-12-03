'use client';
import React from 'react';
import ReactStars from 'react-rating-stars-component';

interface Props {
	grade: number;
}

const StaticStars = ({ grade }: Props) => {
	return (
		<>
			<div
				style={{
					display: 'flex',
					flexDirection: 'column',
					width: '100%',
					border: '1px solid white',
				}}
			>
				<div
					style={{
						display: 'flex',
						flexDirection: 'row',
						justifyContent: 'flex-start',
						alignItems: 'center',
					}}
				>
					<ReactStars
						// count={grade || 0}
						count={5}
						value={grade || 0}
						size={24}
						onChange={(e: number) => alert(e)}
						isHalf={false}
						activeColor="white"
						color="grey"
						edit={false}
					/>
					<p
						style={{
							marginLeft: '10px',
						}}
					>
						별점 {grade || 0} 점
					</p>
				</div>
			</div>
		</>
	);
};

export default StaticStars;
