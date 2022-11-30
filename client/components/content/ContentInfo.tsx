import React, { useState } from "react";
import styles from "./ContentInfo.module.css";
import OrangeButton from "../Buttons/orangeButton";
import { IContent } from "../../types/contents";
import Image from "next/image";
import { ContentCardWishBtn } from "../Buttons/ContentCardWishBtn";
import ContentsIdPage from "../../app/contents/[contentsId]/page";
interface ContentInfoProps {
  contentInfo: IContent;
}

const ContentInfo = ({ contentInfo }: ContentInfoProps) => {
  return (
    <div>
      <div className={styles.thumbnail}>
        <Image
          src={contentInfo.thumbnail}
          alt={contentInfo.title}
          fill={true}
        />
      </div>
      <div className={styles.InfoWrapper}>
        <div className={styles.Info}>
          <div>
            <h3>
              {contentInfo.tutorname}&nbsp; {contentInfo.categories}
            </h3>
          </div>
          <ContentCardWishBtn contentId={contentInfo.contentsId} />
        </div>

        <div className={styles.classWrapper}>
          <div className={styles.classtitle}>
            <h2>{contentInfo.title}</h2>
          </div>
          <h2>{contentInfo.price}</h2>
        </div>
        <h3> 별점{contentInfo.grade}</h3>
        <div className={styles.btn}>
          <OrangeButton name={"강의 구매하기"} />
        </div>
      </div>
      <hr className={styles.line}></hr>
    </div>
  );
};

export default ContentInfo;
