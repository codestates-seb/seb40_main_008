'use client'
import React, { useState } from 'react'
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import styles from './MyclassTab.module.css';

export const handleSortChange = (e: any) => {
    const value = e.target.value;
    console.log('value', value);
    return value;
}

const MyclassTab = () => {

    const [tabIndex, setTabIndex] = useState(0);

    return (
        <Tabs
            selectedIndex={tabIndex}
            onSelect={(tabIndex) => setTabIndex(tabIndex)}
            onChange={(e) => handleSortChange(e)}
        >

            <TabList className={styles.tablist} >
                <Tab className={styles.tab} value='takingclass'>수강중인 클래스</Tab>
                <Tab className={styles.tab} value='wishclass'>찜한 클래스</Tab>
            </TabList>

            <div className={styles.tabpanelWrapper}>
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
                        수강중인 클래스
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
                        찜한 클래스
                    </div>
                </TabPanel>
            </div>
        </Tabs>
    )
}

export default MyclassTab;