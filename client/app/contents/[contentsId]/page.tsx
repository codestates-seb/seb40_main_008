import React from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import ContentInfo from "../../../components/content/ContentInfo";
import ContentTabs from "../../../components/content/ContentTabs";
import { IContent, ICurriculumContent } from "../../../types/contents";

const getContentInfo = async (contentsId: string): Promise<IContent> => {
  const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`, {
    method: "GET",
  });
  const { contentInfo } = await res.json();
  return contentInfo;
};

// const getContentInfo2 = async (): Promise<IContent> => {
//   const res = await fetch(
//     `https://run.mocky.io/v3/1557f956-b2ac-461f-b17d-fd4005379b44`
//   );
//   const { contentInfo } = await res.json();
//   return contentInfo;
// };

const getCurriculum = async (
  contentsId: string
): Promise<Array<ICurriculumContent>> => {
  const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`, {
    method: "GET",
  });
  const { curriculumInfo } = await res.json();
  return curriculumInfo;
};

const ContentsIdPage = async ({ params }: any) => {
  const contentInfo = await getContentInfo(params.contentsId);
  // const contentInfo = await getContentInfo2();
  const curriculumInfo = await getCurriculum(params.contentsId);
  console.log("parm", params);
  return (
    <>
      <BaseNavbar />
      <ContentInfo contentInfo={contentInfo} />
      <ContentTabs contentInfo={contentInfo} curriculumInfo={curriculumInfo} />
    </>
  );
};

export default ContentsIdPage;
