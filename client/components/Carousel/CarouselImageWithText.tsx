import Image from 'next/image';
import React from 'react';
import Link from 'next/link';

interface CarouselProp {
	title: string;
	subtitle: string;
	src: string;
	link: string;
}
//TODO: add gradient to carousel image container
const CarouselImageWithText = ({ title, subtitle, src, link }: CarouselProp) => {
	return (
		<div style={{ position: 'relative' }}>
			<Link href={`/${link}`}>
				<div
					className="img-ctn"
					style={{
						width: '100%',
						height: '450px',
						backgroundImage:
							'linear-gradient(to right, red,orange,yellow,green,blue,indigo,violet)',
						zIndex: 2,
					}}
				>
					<Image
						src={src}
						alt={title + subtitle}
						//fill = true 를 쓰려면 상위 div가 relative
						// width={412}
						// height={462}
						fill={true}
						style={{ objectFit: 'cover' }}
					/>
				</div>
				<div
					style={{
						height: '100px',
						width: '260px',
						// border: "1px solid red",
						position: 'absolute',
						padding: '0 20px',
						fontWeight: 'bold',
						bottom: 70,
					}}
				>
					<h2
						style={{
							color: 'white',
							fontWeight: 'bold',
							textShadow: '4px 2px 2px gray',
						}}
					>
						{title}
					</h2>

					<h3
						style={{
							color: 'gray',
							fontSize: '13px',
							marginTop: '10px',
						}}
					>
						{subtitle}
					</h3>
				</div>
			</Link>
		</div>
	);
};

export default CarouselImageWithText;
