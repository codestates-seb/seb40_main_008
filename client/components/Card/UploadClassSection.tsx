import React from 'react'
import Image from "next/image";
import { ICategorySearchResult } from '../../types/category_search/categorySearchType';
import styles from './UploadClassSection.module.css';
import { titleLengthFormatter } from '../../utils/helper/titleLengthFormatter';

// 수정, 삭제 logic 만들기

interface HomeContentProps {
    contentsList: ICategorySearchResult[];
}

const UploadClassSection = ({ contentsList }: HomeContentProps) => {
    return (
        <div className={styles.myuploadclassWrapper}>
            {contentsList.map((e) => (
                <div key={e.contentsId}>
                    <div key={e.contentsId} className={styles.thumnailImg}>
                        <Image
                            src={e.thumbnail}
                            alt="myuploadclass thumbnail"
                            placeholder="blur"
                            blurDataURL="../public/images/blur.png"
                            fill={true}
                            style={{ objectFit: "cover", borderRadius: "4px" }}
                        />
                    </div>
                    <div className={styles.classinfoWrapper_line1}>
                        <div className={styles.classtitle}>{titleLengthFormatter(e.title)}</div>
                        <div className={styles.classinfoWrapper_line2}>
                            <span className={styles.category_tutor}>{e.categories} / {e.users.userName}</span>
                        </div>
                        <div className={styles.classinfoWrapper_line3}>
                            <span className={styles.btnWrapper}>
                                <button className={styles.btn}>수정</button>
                                <button className={styles.btn}>삭제</button>
                            </span>
                        </div>

                    </div>
                </div>
            ))}

        </div>
    )
}

export default UploadClassSection;