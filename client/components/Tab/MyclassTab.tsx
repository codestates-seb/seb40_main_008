import React from 'react';
import styles from './MyclassTab.module.css';
import { ICategorySearchResult } from '../../types/category_search/categorySearchType';
import HomeClassesSection from '../Card/HomeClassesSection';
import { CustomTab } from './CustomTab';

interface MyclassTabProps {
    takingClasses: Array<ICategorySearchResult>;
    wishClasses: Array<ICategorySearchResult>;
}

const MyclassTab = ({ takingClasses, wishClasses }: MyclassTabProps) => {

    return (

        <CustomTab
            tabs={['수강중인 클래스', '찜한 클래스']}
            contents={[
                <>
                    {
                        takingClasses.length === 0 ?
                            (<div className={styles.tabpannel}>수강중인 클래스가 없습니다.</div>)
                            :
                            <HomeClassesSection contentsList={takingClasses} />
                    }
                </>,
                <>
                    {
                        wishClasses.length === 0 ?
                            (<div className={styles.tabpannel}>찜한 클래스가 없습니다.</div>)
                            :
                            <HomeClassesSection contentsList={wishClasses} />
                    }
                </>
            ]}
        />
    );
};

export default MyclassTab;
