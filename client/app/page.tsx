import HomeNavBar from '../components/HomeNavBar/HomeNavBar';
import TabNavigator from '../components/TabNavigator/TabNavigator';
import { Content } from '../types/rootScreen/mainVideoContents';
import HomeClassesSection from './HomeClassesSection';

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

const page = async () => {
	const contentsList = await getHomeContents();

	return (
		<>
			<HomeNavBar />
			<HomeClassesSection contentList={contentsList} />
			<TabNavigator activeLink={'home'} />
		</>
	);
};

export default page;
