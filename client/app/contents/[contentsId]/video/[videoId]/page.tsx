import React from 'react';
import verifyLogin from '../../../../../utils/VerifyLogin';
import VideoPageSection from './VideoPageSection';

interface VideoIdPageProps {
	params: {
		videoId: string;
	};
}

const getVideoPageContent = async (id: string) => {
	try {
		const res = await fetch(
			`https://run.mocky.io/v3/4ff0e774-4fe1-4022-a420-0b2334bda7a5`
		);
		const data = await res.json();
		return data;
	} catch (error) {
		console.error(error);
	}
};

const VideoIdPage = async ({ params: { videoId } }: VideoIdPageProps) => {
	const userInfo = await verifyLogin();
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
