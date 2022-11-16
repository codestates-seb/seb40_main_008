import Image from 'next/image';
import React from 'react';
import { CarouselInfo } from '../../types/homeScreen/carousel';

interface CarouselProp {
	carousel: CarouselInfo[];
}

const Carousel = ({ carousel }: CarouselProp) => {
	return (
		<>
			{carousel.map((e: CarouselInfo) => (
				<Image alt="carousel image" src={e.src} key={e.id} width={300} height={300} />
			))}
		</>
	);
};

export default Carousel;
