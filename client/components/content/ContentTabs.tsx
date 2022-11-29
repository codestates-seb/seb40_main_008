"use client";
import React, { useState } from "react";
import styles from "./ContentTabs.module.css";
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import "react-tabs/style/react-tabs.css";
import CurriculumInfo from "./CurriculumInfo";
import { IContent, ICurriculumContent } from "../../types/contents";

interface ContentTabsProps {
  contentInfo: IContent;
  curriculumInfo: ICurriculumContent[];
}

const ContentTabs = ({ contentInfo, curriculumInfo }: ContentTabsProps) => {
  const [tabIndex, setTabIndex] = useState(0);
  return (
    <>
      <Tabs
        selectedIndex={tabIndex}
        onSelect={(tabIndex) => setTabIndex(tabIndex)}
      >
        <TabList>
          <Tab>강의소개</Tab>
          <Tab>강사소개</Tab>
          <Tab>커리큘럼</Tab>
        </TabList>
        <TabPanel>
          <div
            style={{
              width: "90%",
              padding: "20px",
              height: "100%",
              border: "1px solid white",
              margin: "20px auto",
            }}
          >
            {contentInfo.details}
          </div>
        </TabPanel>
        <TabPanel>
          <div
            style={{
              width: "90%",
              padding: "20px",
              height: "100%",
              border: "1px solid white",
              margin: "20px auto",
            }}
          >
            {contentInfo.tutorDetail}
          </div>
        </TabPanel>
        <TabPanel>
          <CurriculumInfo
            contentsId={contentInfo.contentsId}
            curriculumInfo={curriculumInfo}
          />
        </TabPanel>
      </Tabs>
    </>
  );
};
export default ContentTabs;
