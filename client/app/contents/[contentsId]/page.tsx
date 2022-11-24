import React, { use } from "react";
import BaseNavbar from "../../../components/BaseNavBar/BaseNavbar";
import ContentInfo from "../../../components/content/ContentInfo";
import { IContent } from "../../../types/contents";
import styles from "./content.module.css";
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

const getContentInfo = async (contentsId: string): Promise<Array<IContent>> => {
  const res = await fetch(`https://pioneroroom.com/contents/${contentsId}`);
  const { content } = await res.json();
  return content;
};

const ContentsIdPage = async ({ params }: any) => {
  const ee = params.contentsId;
  const post = await getContentInfo(params.contentsId);

  return (
    <>
      <BaseNavbar />
      <ContentInfo />
      <ContentTabs />
    </>
  );
};

export default ContentsIdPage;
