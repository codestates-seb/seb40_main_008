"use client";
import React, { useState } from "react";
import styles from "./ContentTabs.module.css";
import CurriculumInfo from "./CurriculumInfo";
import { IContent, ICurriculumContent } from "../../types/contents";
import { CustomTab } from "../Tab/CustomTab";
import IUserInfo from "../../types/user/userinfo";

interface ContentTabsProps {
  contentInfo: IContent;
  curriculumInfo: ICurriculumContent[];
  userInfo: IUserInfo;
}

const ContentTabs = ({
  contentInfo,
  curriculumInfo,
  userInfo,
}: ContentTabsProps) => {
  return (
    <>
      <CustomTab
        tabs={["강의소개", "강사소개", "커리큘럼"]}
        contents={[
          <>
            {contentInfo?.details.length === 0 ? (
              <div className={styles.tabpannel}>강의 소개가 없습니다.</div>
            ) : (
              <div
                key={contentInfo?.contentsId}
                style={{
                  width: "90%",
                  padding: "20px",
                  height: "100%",
                  border: "1px solid white",
                  margin: "60px auto 20px auto",
                }}
              >
                {contentInfo?.details}
              </div>
            )}
          </>,
          <>
            {contentInfo?.details.length === 0 ? (
              <div className={styles.tabpannel}>강사 소개가 없습니다.</div>
            ) : (
              <div
                key={contentInfo?.contentsId}
                style={{
                  width: "90%",
                  padding: "20px",
                  height: "100%",
                  border: "1px solid white",
                  margin: "60px auto 20px auto",
                }}
              >
                {contentInfo?.tutorDetail}
              </div>
            )}
          </>,
          <>
            {contentInfo?.details.length === 0 ? (
              <div key={contentInfo?.contentsId} className={styles.tabpannel}>
                커리큘럼이 없습니다.
              </div>
            ) : (
              <CurriculumInfo
                userInfo={userInfo}
                role={contentInfo?.role}
                contentsId={contentInfo?.contentsId}
                curriculumInfo={curriculumInfo}
              />
            )}
          </>,
        ]}
      />
    </>
  );
};
export default ContentTabs;
