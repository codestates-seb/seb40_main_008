import { cookies } from 'next/headers';
import React from 'react';
import verifyLogin from '../../../../../utils/VerifyLogin';
import VideoPageSection from './VideoPageSection';

interface VideoIdPageProps {
	params: {
		videoId: string;
		contentsId: string;
	};
}

const getVideoPageContent = async (contentsId: string, videoId: string) => {
	const token = cookies().get('accessToken')?.value;
	try {
		const res = await fetch(
			`https://pioneroroom.com/auth/contents/${contentsId}/video/${videoId}`,
			{
				method: 'GET',
				headers: {
					Authorization: `Bearer ${token}`,
				},
			}
		);

		const data = await res.json();
		return data;
	} catch (error) {
		console.error(error);
	}
};

const VideoIdPage = async ({
	params: { videoId, contentsId },
}: VideoIdPageProps) => {
	const userInfo = await verifyLogin();
	const data = await getVideoPageContent(contentsId, videoId);
	console.log('🚀 ~ file: page.tsx:38 ~ data', data);

	return (
		<VideoPageSection
			data={data}
			contentsId={contentsId}
			uploadClassId={videoId}
		/>
	);
};

export default VideoIdPage;
