"use client";
import React from "react";
import Image from "next/image";
import { ICategorySearchResult } from "../../types/category_search/categorySearchType";
import styles from "./UploadClassSection.module.css";
import { titleLengthFormatter } from "../../utils/helper/titleLengthFormatter";
import DelEditbtn from "../Buttons/DelEditbtn";
import Link from "next/link";
import Curriculumdelete from "../Buttons/Curriculumdelete";
import Categories from "../../app/categories/page";
// 수정, 삭제 버튼 logic 추가

interface HomeContentProps {
  contentsList: ICategorySearchResult[];
}

const UploadClassSection = ({ contentsList }: HomeContentProps) => {
  return (
    <div className={styles.myuploadclassWrapper}>

      {contentsList.length === 0 ?
        <div className={styles.noclassWrapper}>내가올린 클래스가 없습니다.</div>
        :
        contentsList.map((e, index) => (
          <div key={index}>
            <Link href={`/contents/${e.contentsId}`}>
              <div key={e.contentsId} className={styles.thumnailImg}>
                <Image
                  src={e.thumbnail}
                  alt="myuploadclass thumbnail"
                  placeholder="blur"
                  blurDataURL="../public/images/blur.png"
                  fill={true}
                  style={{
                    objectFit: "cover",
                    borderRadius: "4px",
                    cursor: "pointer",
                  }}
                />
              </div>
            </Link>

            <div className={styles.classinfoWrapper_line1}>
              <div className={styles.classtitle}>
                {titleLengthFormatter(e.title)}
              </div>
              <div className={styles.classinfoWrapper_line2}>
                <span className={styles.category_tutor}>
                  {e.categories} / {e.users.userName}
                </span>
              </div>
              <div className={styles.classinfoWrapper_line3}>
                <span className={styles.btnWrapper}>
                  {/* <Link href={{
                        pathname: "/upload",
                        query:{
                          slug:"edit",
                          title: e.title,
                          Categories: e.categories,
                          details: 
                          tutorDetail:
                          thumbnail:
                          price:

                        }
                  }}> */}
                  <button className={styles.btn}>수정</button>
                  {/* </Link> */}
                  <Curriculumdelete
                    url={`https://pioneroroom.com/auth/contents/`}
                    Id={e.contentsId}
                  />
                </span>
              </div>
            </div>
          </div>
        ))
      }
    </div>
  );
};

export default UploadClassSection;
