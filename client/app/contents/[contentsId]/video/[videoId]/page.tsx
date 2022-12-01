import { cookies } from 'next/headers';
import React from 'react';
import HomeNavBar from '../../../../../components/HomeNavBar/HomeNavBar';
import TabNavigator from '../../../../../components/TabNavigator/TabNavigator';
import getUserInfo from '../../../../../utils/helper/backendUserInfo';

interface VideoIdPageProps {
	params: {
		videoId: string;
	};
}

const getVideoPageContent = async () => {};

const VideoIdPage = async ({ params: { videoId } }: VideoIdPageProps) => {
	console.log('🚀 ~ file: page.tsx:12 ~ VideoIdPage ~ videoId', videoId);
	const userInfo = await getUserInfo(cookies().get('accessToken'));

	if (!userInfo) {
		return (
			<div>
				<p>invalid token</p>
			</div>
		);
	}
	return (
		<>
			<div>{JSON.stringify(userInfo)}</div>
		</>
	);
};

export default VideoIdPage;
