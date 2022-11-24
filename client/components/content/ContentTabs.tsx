"use client";
import React, { useState } from "react";
import styles from "./ContentTabs.module.css";
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import "react-tabs/style/react-tabs.css";
import CurriculumInfo from "./CurriculumInfo";
const ContentTabs = () => {
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
        <TabPanel>t1t1t1</TabPanel>
        <TabPanel>t2t2t2</TabPanel>
        <TabPanel>
          <CurriculumInfo />
        </TabPanel>
      </Tabs>
    </>
  );
};
export default ContentTabs;
