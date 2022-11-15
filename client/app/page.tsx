import React from 'react';
import QuestionCard from '../components/Card/QuestionCard';
import Carousel from '../components/Carousel/Carousel';
import HomeNavBar from '../components/HomeNavBar/HomeNavBar';
import { ICarousel } from '../types/carousel';
import TabNavigator from '../components/TabNavigator/TabNavigator';
import { Content } from '../types/rootScreen/mainVideoContents';
import HomeContentSection from './HomeContentSection';

const getHomeContents = async (): Promise<Array<Content>> => {
	try {
		const response = await fetch('https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109', {
			next: {
				revalidate: 60,
			},
		});
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const page = async ({ Question }: any) => {
	// const imageArr = await getCarouselImages();
	const contentsList = await getHomeContents();

	return (
		<div className="main">
			<HomeNavBar />
			{/* <Carousel carousel={imageArr} /> */}
			<HomeContentSection contentList={contentsList} />
			<TabNavigator activeLink={''} />
		</div>
	);
};

export default page;
