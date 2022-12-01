'use client';
import React, { useState } from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import styles from './MyclassTab.module.css';
import { ICategorySearchResult } from '../../types/category_search/categorySearchType';
import HomeClassesSection from '../Card/HomeClassesSection';

interface MyclassTabProps {
    takingClasses: Array<ICategorySearchResult>;
    wishClasses: Array<ICategorySearchResult>;
}

const MyclassTab = ({ takingClasses, wishClasses }: MyclassTabProps) => {
    const [tabIndex, setTabIndex] = useState(0);
    return (
        <Tabs
            selectedIndex={tabIndex}
            onSelect={(tabIndex) => setTabIndex(tabIndex)}
        >
            <TabList className={styles.tablist}>
                {
                    tabIndex === 0 ?
                        <Tab className={styles.select_tab}>수강중인 클래스</Tab>
                        :
                        <Tab className={styles.tab}>수강중인 클래스</Tab>
                }

                {
                    tabIndex === 1 ?
                        <Tab className={styles.select_tab}>찜한 클래스</Tab>
                        :
                        <Tab className={styles.tab}>찜한 클래스</Tab>
                }
            </TabList>
            <TabPanel>
                {
                    takingClasses === undefined ?
                        (<div className={styles.tabpannel}>수강중인 클래스가 없습니다.</div>)
                        :
                        <HomeClassesSection contentsList={takingClasses} />
                }

            </TabPanel>
            <TabPanel>
                {
                    wishClasses === undefined ?
                        (<div className={styles.tabpannel}>찜한 클래스가 없습니다.</div>)
                        :
                        <HomeClassesSection contentsList={wishClasses} />
                }

            </TabPanel>
        </Tabs>
    );
};

export default MyclassTab;
