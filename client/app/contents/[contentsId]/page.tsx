import React from 'react';
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import ContentInfo from '../../../components/content/ContentInfo';
import ContentTabs from '../../../components/content/ContentTabs';
import { ContentsWithCurriculum, IContent, ICurriculumContent } from '../../../types/contents';
import { cookies } from 'next/headers';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';
import { ILoopIDList } from '../../../types/detailedContentIdListType';
import verifyLogin from '../../../utils/VerifyLogin';
import { getCookie } from 'cookies-next';

const getContentInfo = async (contentsId: string): Promise<ContentsWithCurriculum> => {
	const token = cookies().get('accessToken')?.value;
	const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`, {
		method: 'GET',
		headers: {
			Authorization: `Bearer ${token}`,
		},
	});

	const data = await res.json();
	return data;
};

const ContentsIdPage = async ({ params }: any) => {
	const {contentInfo, curriculumInfo} = await getContentInfo(params.contentsId);
	const userInfo = await verifyLogin();
	const uploadclassId = getUploadClassId(curriculumInfo);

	return (
		<>
			<BaseNavbar />
			<ContentInfo uploadclassId={uploadclassId} contentInfo={contentInfo} />
			<ContentTabs
				userInfo={userInfo}
				contentInfo={contentInfo}
				curriculumInfo={curriculumInfo}
			/>
		</>
	);
};

export async function generateStaticParams() {
	const res = await fetch('https://pioneroroom.com/contents');
	const posts: ILoopIDList[] = await res.json();
	const arr = posts.map((post) => {
		return {
			contentsId: String(post.contentsId),
		};
	});
	return arr;
}

export default ContentsIdPage;

function getUploadClassId(curriculumInfo: ICurriculumContent[]) {
	return curriculumInfo[0]?.uploadClassList[0]
		? curriculumInfo[0].uploadClassList[0].uploadClassId
		: null;
}
