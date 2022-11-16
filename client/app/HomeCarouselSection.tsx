import React from 'react';
import { CarouselInfo } from '../types/homeScreen/carousel';

interface HomeCarouselSectionProps {
	carouselList: Array<CarouselInfo>;
}

const HomeCarouselSection = ({ carouselList }: HomeCarouselSectionProps) => {
	return <div>{JSON.stringify(carouselList)}</div>;
};

export default HomeCarouselSection;
