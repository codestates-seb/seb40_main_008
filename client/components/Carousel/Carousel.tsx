import Image from 'next/image';
import React from 'react';
import { ICarousel } from '../../types/homeScreen/carousel';

interface CarouselProp {
	carousel: ICarousel[];
}

const Carousel = ({ carousel }: CarouselProp) => {
	return (
		<>
			{carousel.map((e: ICarousel) => (
				<Image alt="carousel image" src={e.src} key={e.id} width={300} height={300} />
			))}
		</>
	);
};

export default Carousel;
