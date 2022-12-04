import Image from 'next/image';
import React from 'react';

interface Props {
	tutorName: string;
	title: string;
	thumbnail: string;
}

const UpperTextContainer = ({ thumbnail, tutorName, title }: Props) => {
	return (
		<div
			style={{
				marginTop: '50px',
				// border: '1px solid white',
				width: '92%',
				height: '120px',
				backgroundColor: '#F3F3F3',
				borderRadius: '10px',
				display: 'flex',
				justifyContent: 'center',
				alignItems: 'center',
				flexDirection: 'column',
			}}
		>
			<div
				style={{
					// border: '1px solid blue',
					display: 'flex',
					justifyContent: 'center',
					alignItems: 'center',
					flexDirection: 'row',
					width: '92%',
					position: 'relative',
				}}
			>
				<div
					style={{
						// border: '1px solid green',
						width: '120px',
						height: '100px',
						position: 'absolute',
						left: 0,
						display: 'flex',
						justifyContent: 'center',
						alignItems: 'center',
						flexDirection: 'row',
						borderRadius: '10px',
						overflow: 'hidden',
					}}
				>
					<Image
						alt="thumbnail"
						src={thumbnail}
						sizes="(max-width: 768px) 100vw,
						(max-width: 1200px) 50vw,
						33vw"
						fill={true}
						style={{ objectFit: 'cover' }}
					/>
				</div>
				<div
					style={{
						display: 'flex',
						justifyContent: 'flex-end',
						alignItems: 'center',
						flexDirection: 'column',
						marginLeft: '20px',
						height: '120px',
						width: '500px',
						// border: '1px solid red',
					}}
				>
					<div
						style={{
							display: 'flex',
							justifyContent: 'center',
							alignItems: 'flex-start',
							flexDirection: 'column',
							width: '100%',
							height: '100%',
							// border: '1px solid blue',
							paddingLeft: '100px',
						}}
					>
						<h3 style={{ color: 'black' }}>{title}</h3>
						<h3 style={{ color: 'black', marginTop: '10px' }}>
							{tutorName}
						</h3>
					</div>
				</div>
			</div>
		</div>
	);
};

export default UpperTextContainer;
