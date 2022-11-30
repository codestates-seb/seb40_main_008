import React from 'react'
import styles from './UploadClassSection.module.css';

const UploadClassSection = () => {
    return (
        <div className={styles.myuploadclassWrapper}>
            <div className={styles.thumnailImg}>강좌 썸네일</div>
            <div className={styles.classinfoWrapper_line1}>
                <div className={styles.classtitle}>강좌 제목</div>
                <div className={styles.classinfoWrapper_line2}>
                    <span className={styles.category_tutor}>강좌 주제/업로더</span>
                    <span className={styles.btnWrapper}>
                        <button className={styles.btn}>수정</button>
                        <button className={styles.btn}>삭제</button>
                    </span>
                </div>
            </div>
        </div>
    )
}

export default UploadClassSection;