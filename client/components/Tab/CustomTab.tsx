'use client';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import React, { useState } from 'react';
import styles from './CustomTab.module.css';

// 형태
// <CustomTab
// tabs={['탭제목1', '탭제목2', '탭제목3']}
// contents={[
//     <CurriculumPanel curriculum={Question.curriculum}></CurriculumPanel>
//     <CurriculumPanel curriculum={Question.curriculum}></CurriculumPanel>
//     <CurriculumPanel curriculum={Question.curriculum}></CurriculumPanel>
// ]}
// />

interface CustomTabProps {
	tabs: string[];
	contents: JSX.Element[];
}

export const CustomTab = ({ tabs, contents }: CustomTabProps) => {
	const [tabIndex, setTabIndex] = useState(0);
	return (
		<Tabs
			selectedIndex={tabIndex}
			onSelect={(tabIndex) => setTabIndex(tabIndex)}
		>
			<TabList className={styles.tablist}>
				{tabs.map((el, idx) => {
					return tabIndex === idx ? (
						<Tab key={idx} className={styles.select_tab}>
							{el}
						</Tab>
					) : (
						<Tab key={idx} className={styles.tab}>
							{el}
						</Tab>
					);
				})}
			</TabList>
			{contents.map((el, idx) => {
				return <TabPanel key={idx}>{el}</TabPanel>;
			})}
		</Tabs>
	);
};
