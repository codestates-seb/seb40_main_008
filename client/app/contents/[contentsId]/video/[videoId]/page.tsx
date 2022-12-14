import { cookies } from 'next/headers';
import { redirect } from 'next/navigation';
import React from 'react';
import BaseNavbar from '../../../../../components/BaseNavBar/BaseNavbar';
import { ILoopIDList } from '../../../../../types/detailedContentIdListType';
import verifyLogin from '../../../../../utils/VerifyLogin';
import VideoPageSection from './VideoPageSection';

interface VideoIdPageProps {
	params: {
		videoId: string;
		contentsId: string;
	};
	searchParams?: any;
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

//BUG: PageProps searchParams type definition does not work
// If it is required.
const VideoIdPage = async ({ params, searchParams }: VideoIdPageProps) => {
	const { videoId, contentsId } = params;
	const { status } = searchParams;

	const userInfo = await verifyLogin();
	const data = await getVideoPageContent(contentsId, videoId);

	if (!userInfo) redirect(`/contents/${contentsId}`);

	return (
		<>
			<BaseNavbar page={'back'} />
			<VideoPageSection
				status={status}
				data={data}
				contentsId={contentsId}
				uploadClassId={videoId}
			/>
		</>
	);
};

export default VideoIdPage;

export async function generateStaticParams() {
	const paramList: VideoIdPageProps['params'][] = [];
	const res = await fetch('https://pioneroroom.com/contents');
	const posts: ILoopIDList[] = await res.json();
	for (const post of posts) {
		const paramArr: any = [];
		if (!post.chapterList.length) continue;
		for (const chapter of post.chapterList) {
			if (!chapter.uploadClassList.length) continue;
			for (const videoId of chapter.uploadClassList) {
				paramArr.push({
					videoId: String(videoId),
					contentsId: String(post.contentsId),
				});
			}
		}
		paramList.push(...paramArr);
	}
	return paramList;
}
