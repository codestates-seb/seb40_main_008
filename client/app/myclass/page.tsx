import React from 'react'
import styles from './myclass.module.css';
import TabNavigator from '../../components/TabNavigator/TabNavigator';
import { ICategorySearchResult } from '../../types/homeScreen/mainVideoContents';
import HomeClassesSection from '../../components/Card/HomeClassesSection';
import MyclassTab from '../../components/Tab/MyclassTab';

const getIncourseContents = async (): Promise<Array<ICategorySearchResult>> => {
    try {
        // request url : https://pioneroroom.com/auth/myuploadclass
        const response = await fetch(
            `https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818`,
            {
                next: {
                    revalidate: 60,
                },
            }
        );
        const { contentsList } = await response.json();
        return contentsList;
    } catch (error) {
        console.error(error);
        return [];
    }
};

const MyclassPage = async () => {

    // 탭끼리 눌렀을 때 조건벌로 분기해서 contentsList 받아오기
    let contentsList = await getIncourseContents();


    return (
        <>
            <div className={styles.title}>내 클래스</div>
            <MyclassTab />

            <TabNavigator activeLink={'myclass'} />
        </>
    )
}

export default MyclassPage;