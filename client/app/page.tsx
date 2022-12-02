import HomeNavBar from '../components/HomeNavBar/HomeNavBar';
import TabNavigator from '../components/TabNavigator/TabNavigator';
import { CarouselInfo } from '../types/homeScreen/carousel';
import { ICategorySearchResult } from '../types/category_search/categorySearchType';
import HomeCarouselSection from './HomeCarouselSection';
import HomeClassesSection from '../components/Card/HomeClassesSection';
import styles from './styles/page.module.css';
import CarouselImageWithText from '../components/Carousel/CarouselImageWithText';
import { cookies, headers } from 'next/headers';
import getUserInfo from '../utils/helper/backendUserInfo';
import verifyLogin from '../utils/VerifyLogin';

const getClassesContents = async (): Promise<Array<ICategorySearchResult>> => {
	try {
		const response = await fetch('https://pioneroroom.com/home', {
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

const getCarouselInfo = async (): Promise<Array<CarouselInfo>> => {
	try {
		const response = await fetch('https://run.mocky.io/v3/8b2e18b5-ed87-4550-8985-191aff20c160');
		const { carouselInfo } = await response.json();
		return carouselInfo;
	} catch (error) {
		alert(error);
		return [];
	}
};

const page = async () => {
	// console.log('cookies().get(accessToken).value', cookies().get('accessToken')?.value)
	const userInfo = await verifyLogin();
	// console.log('🚀 ~ file: page.tsx ~ line 44 ~ page ~ userInfo', userInfo);
	const contentsList = await getClassesContents();
	const carouselList = await getCarouselInfo();

	return (
		<>
			<HomeNavBar userInfo={userInfo} />
			<HomeCarouselSection>
				{carouselList.map((e) => (
					<CarouselImageWithText
						key={e.imageUrl}
						title={e.title}
						subtitle={e.subTitle}
						src={e.imageUrl}
						link={e.redirectUrl}
					/>
				))}
			</HomeCarouselSection>
			<div>{JSON.stringify(userInfo)}</div>
			<HomeClassesSection contentsList={contentsList} />
			<TabNavigator activeLink={'home'} />
		</>
	);
};

export default page;
