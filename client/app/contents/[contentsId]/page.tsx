import { Content } from '@next/font/google';
import React from 'react';
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import ContentInfo from '../../../components/content/ContentInfo';
import ContentTabs from '../../../components/content/ContentTabs';

import { IContent, ICurriculumContent } from '../../../types/contents';
import styles from './content.module.css';
// const getContentInfo = async (contentsId: string): Promise<Array<IContent>> => {
//   try {
//     const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`);
//     const { content } = await res.json();
//     return content;
//   } catch (error) {
//     alert(error);
//     return [];
//   }
// };

// const getContentInfo = async (contentsId: string): Promise<Array<IContent>> => {
//   const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`);
//   const { content } = await res.json();
//   return content;
// };
const getContentInfo2 = async (): Promise<IContent> => {
	const res = await fetch(
		`https://run.mocky.io/v3/1557f956-b2ac-461f-b17d-fd4005379b44`
	);
	const { contentInfo } = await res.json();
	return contentInfo;
};

const getCurriculum = async (): Promise<Array<ICurriculumContent>> => {
	const res = await fetch(
		`https://run.mocky.io/v3/1557f956-b2ac-461f-b17d-fd4005379b44`
	);
	const { curriculumInfo } = await res.json();
	return curriculumInfo;
};

const ContentsIdPage = async ({ params }: any) => {
	const contentInfo = await getContentInfo2();
	const curriculumInfo = await getCurriculum();
	return (
		<>
			<BaseNavbar />
			<ContentInfo contentInfo={contentInfo} />
			<ContentTabs
				contentInfo={contentInfo}
				curriculumInfo={curriculumInfo}
			/>
		</>
	);
};

export default ContentsIdPage;
