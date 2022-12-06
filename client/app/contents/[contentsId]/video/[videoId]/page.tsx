import { cookies } from 'next/headers';
import Link from 'next/link';
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
	searchParams: {
		status: string;
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

const VideoIdPage = async ({ params, searchParams }: VideoIdPageProps) => {
	const { videoId, contentsId } = params;
	const { status } = searchParams;
	console.log('🚀 ~ file: page.tsx:42 ~ VideoIdPage ~ status', status);

	const userInfo = await verifyLogin();
	const data = await getVideoPageContent(contentsId, videoId);

	if (!userInfo) redirect(`/contents/${contentsId}`);
	if (status === 'Unpaid_customer') {
		return (
			<>
				<BaseNavbar page="back" />
				<section
					style={{
						display: 'flex',
						justifyContent: 'center',
						alignItems: 'center',
						height: '100vh',
						flexDirection: 'column',
					}}
				>
					<h2>강의를 먼저 구매해주셔야 합니다</h2>
					<Link href={`/contents/${contentsId}`}>뒤로가기</Link>
				</section>
			</>
		);
	}
	return (
		<>
			<BaseNavbar page={'back'} />
			<VideoPageSection
				data={data}
				contentsId={contentsId}
				uploadClassId={videoId}
			/>
		</>
	);
};

export default VideoIdPage;

// export async function generateStaticParams() {
//   const paramList: VideoIdPageProps['params'][] = [];
//   const res = await fetch('https://pioneroroom.com/contents');
//   const posts: ILoopIDList[] = await res.json();
//   for (const post of posts) {
//     const paramArr: any = [];
//     if (!post.chapterList.length) continue;
//     for (const chapter of post.chapterList) {
//       if (!chapter.uploadClassList.length) continue;
//       for (const videoId of chapter.uploadClassList) {
//         paramArr.push({
//           videoId: String(videoId),
//           contentsId: String(post.contentsId),
//         });
//       }
//     }
//     paramList.push(...paramArr);
//   }
//   return paramList;
// }
