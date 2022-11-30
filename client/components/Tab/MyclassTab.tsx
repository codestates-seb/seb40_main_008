'use client'
import React, { useState } from 'react'
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import styles from './MyclassTab.module.css';
import { useRouter } from 'next/navigation';

const MyclassTab = () => {

    const [tabIndex, setTabIndex] = useState(0);
    // console.log('tabIndex', tabIndex);

    // const router = useRouter();
    // if (tabIndex === 0) {
    //     router.push(`/myclass#takingclass`);
    // } else if (tabIndex === 1) {
    //     router.push(`/myclass#wishclass`);
    // }

    return (
        <Tabs
            selectedIndex={tabIndex}
            onSelect={(tabIndex) => setTabIndex(tabIndex)}
        >

            <TabList className={styles.tablist} >
                <Tab className={styles.tab} value='takingclass'>수강중인 클래스</Tab>
                <Tab className={styles.tab} value='wishclass'>찜한 클래스</Tab>
            </TabList>

            {/* <div className={styles.tabpanelWrapper}> */}
            <TabPanel>
                <div
                    style={{
                        width: "100%",
                        padding: "20px",
                        height: "0px",
                        border: "1px solid white",
                        margin: "20px auto",
                    }}
                >
                </div>
            </TabPanel>
            <TabPanel>
                <div
                    style={{
                        width: "90%",
                        padding: "20px",
                        height: "0px",
                        border: "1px solid white",
                        margin: "20px auto",
                    }}
                >
                </div>
            </TabPanel>
            {/* </div> */}
        </Tabs>
    )
}

export default MyclassTab;