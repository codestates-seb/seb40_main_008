import React from 'react'
import BaseNavbar from '../../../components/BaseNavBar/BaseNavbar';
import styles from './myuploadclass.module.css';

const MyUploadClassPage = () => {
    return (
        <>
            <BaseNavbar />
            <div className={styles.myuploadclassWrapper}>
                <div>강좌 썸네일</div>
                <div>강좌 제목</div>
                <div>
                    <span>강좌 주제/업로더</span>
                    <span>
                        <button>수정</button>
                        <button>삭제</button>
                    </span>
                </div>
            </div>
        </>
    )
}

export default MyUploadClassPage;