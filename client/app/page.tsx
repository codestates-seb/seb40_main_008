import HomeNavBar from '../components/HomeNavBar/HomeNavBar';
import TabNavigator from '../components/TabNavigator/TabNavigator';
import { CarouselInfo } from '../types/homeScreen/carousel';
import { ICategorySearchResult } from '../types/category_search/categorySearchType';
import HomeCarouselSection from './HomeCarouselSection';
import HomeClassesSection from '../components/Card/HomeClassesSection';
import styles from './styles/page.module.css';
import CarouselImageWithText from '../components/Carousel/CarouselImageWithText';

const getClassesContents = async (): Promise<Array<ICategorySearchResult>> => {
	try {
		const response = await fetch(
			'https://run.mocky.io/v3/3990c908-5af6-4850-9501-fa41adb80109',
			{
				next: {
					revalidate: 60,
				},
			}
		);
		const { contentsList } = await response.json();
		return contentsList;
	} catch (error) {
		console.error(error);
		return [];
	}
};

const getCarouselInfo = async (): Promise<Array<CarouselInfo>> => {
	try {
		const response = await fetch('https://pioneroroom.com/carousel');
		const { carouselInfo } = await response.json();
		return carouselInfo;
	} catch (error) {
		alert(error);
		return [];
	}
};

const page = async () => {
	const contentsList = await getClassesContents();
	const carouselList = await getCarouselInfo();

	return (
		<>
			<HomeNavBar />
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
		</>
	);
};

export default page;

// main page
// {
//   "contentsList": [
//     {
//       "contentsId": 8,
//       "title": "제목입니다",
//       "thumbnail": "https://s3.ap-northeast-2.amazonaws.com/s3-main-008/contents/thumbnail/25981af8-afba-4b23-890f-05cfc8d3b93e_KakaoTalk_20220619_173347706.png",
//       "likesCount": 3,
//       "categories": "마켓팅",
//       "users": {
//         "usersId": 2,
//         "userName": "테스트유저2",
//         "profileImage": "유저2 프로필 이미지"
//       }
//     },
//   ]
// }

// category search
// [
//   {
//     "contentsId": 4,
//     "title": "제목입니다",
//     "thumbnail": "https://s3.ap-northeast-2.amazonaws.com/s3-main-008/contents/thumbnail/0fb87848-eb33-40cb-81ef-fc904c1feace_KakaoTalk_20220619_173347706.png",
//     "categories": "음악",
//     "users": {
//       "usersId": 1,
//       "userName": "테스트유저1",
//       "profileImage": "유저1 프로필 이미지"
//     }
//   },
// ]
