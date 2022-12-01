import React from 'react'
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import UploadClassSection from '../../../components/Card/UploadClassSection';
import { ICategorySearchResult } from '../../../types/category_search/categorySearchType';

// 로그인 로직 추가해야 함.
// http://pioneroroom/auth/mypage/myuploadclass

const getMyUploadClasses = async (): Promise<Array<ICategorySearchResult>> => {

    // const token = cookies().get('accessToken')?.value;

    try {
        const response = await fetch(`https://run.mocky.io/v3/072e5b64-e3fb-4b38-aa50-313b8b680818`,
            // {
            //     method: 'GET',
            //     headers: {
            //         'Content-Type': 'application/json',
            //         Authorization: `Bearer ${token}`,
            //     },
            // }
        );
        const { contentsList } = await response.json();
        return contentsList;
    } catch (error) {
        console.error(error);
        return [];
    }
};

const MyUploadClassPage = async () => {

    const myUploadclass = await getMyUploadClasses();

    return (
        <>
            <BaseNavbar />
            <UploadClassSection contentsList={myUploadclass} />
        </>
    )
}

export default MyUploadClassPage;