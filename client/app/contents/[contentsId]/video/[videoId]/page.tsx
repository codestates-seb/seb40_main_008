import { cookies } from 'next/headers';
import React from 'react';
import HomeNavBar from '../../../../../components/HomeNavBar/HomeNavBar';
import TabNavigator from '../../../../../components/TabNavigator/TabNavigator';
import getUserInfo from '../../../../../utils/helper/backendUserInfo';
import VideoPageSection from './VideoPageSection';

interface VideoIdPageProps {
	params: {
		videoId: string;
	};
}

const getVideoPageContent = async (id: string) => {
	try {
		const res = await fetch(
			`https://run.mocky.io/v3/c0c7dcd0-4ac8-4d00-a958-713cae6af257`
		);
		const data = await res.json();
		return data;
	} catch (error) {
		console.error(error);
	}
};

const VideoIdPage = async ({ params: { videoId } }: VideoIdPageProps) => {
	const userInfo = await getUserInfo(cookies().get('accessToken'));
	const data = await getVideoPageContent(videoId);

	if (!userInfo) {
		return (
			<div>
				<p>invalid token</p>
			</div>
		);
	}
	return <VideoPageSection data={data} />;
};

export default VideoIdPage;
