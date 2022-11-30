'use client';
import React, { useState } from 'react';
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs';
import styles from './MyclassTab.module.css';
import { useRouter } from 'next/navigation';
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
                <Tab className={styles.tab}>수강중인 클래스</Tab>
                <Tab className={styles.tab}>찜한 클래스</Tab>
            </TabList>
            <TabPanel>
                <HomeClassesSection contentsList={takingClasses} />
            </TabPanel>
            <TabPanel>
                <HomeClassesSection contentsList={wishClasses} />
            </TabPanel>
        </Tabs>
    );
};

export default MyclassTab;
