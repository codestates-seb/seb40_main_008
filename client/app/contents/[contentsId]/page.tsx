import React from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import ContentInfo from "../../../components/content/ContentInfo";
import ContentTabs from "../../../components/content/ContentTabs";
import { IContent, ICurriculumContent } from "../../../types/contents";
import { cookies } from "next/headers";
import { ICategorySearchResult } from "../../../types/category_search/categorySearchType";

const getContentInfo = async (contentsId: string): Promise<IContent> => {
  const token = cookies().get("accessToken")?.value;
  const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  const { contentInfo } = await res.json();
  return contentInfo;
};

const getCurriculum = async (
  contentsId: string
): Promise<Array<ICurriculumContent>> => {
  const token = cookies().get("accessToken")?.value;
  const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  const { curriculumInfo } = await res.json();
  return curriculumInfo;
};

const ContentsIdPage = async ({ params }: any) => {
  const contentInfo = await getContentInfo(params.contentsId);
  const curriculumInfo = await getCurriculum(params.contentsId);
  const uploadclassId = curriculumInfo[0].uploadClassList[0].uploadClassId;
  return (
    <>
      <BaseNavbar />
      <ContentInfo uploadclassId={uploadclassId} contentInfo={contentInfo} />
      <ContentTabs contentInfo={contentInfo} curriculumInfo={curriculumInfo} />
    </>
  );
};

interface ContentId {
	contentsId: number;
}

/*
export async function generateStaticParams() {
	const res = await fetch('https://pioneroroom.com/contents');
	const posts: ContentId[] = await res.json();
	const arr = posts.map((obj) => ({
		contentsId: String(obj.contentsId),
	}));
	return arr;
}
*/

export default ContentsIdPage;
