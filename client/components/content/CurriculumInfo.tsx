import React from "react";
import styles from "./CurriculumInfo.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil } from "@fortawesome/free-solid-svg-icons";
import { ICurriculumContent } from "../../types/contents";
import Image from "next/image";
import Link from "next/link";
import { fetchDelete, fetchEditChapter } from "../../utils/api/fetchDelete";
import { UploadChapterType } from "../../types/uploadclass";
interface CurriculumInfoProps {
  curriculumInfo: ICurriculumContent[];
  contentsId: number;
}

const CurriculumInfo = ({
  contentsId,
  curriculumInfo,
}: CurriculumInfoProps) => {
  console.log("dadsa", contentsId);

  const handleChapterDeleteClick = async (chapterId: number) => {
    try {
      const status = await fetchDelete(
        `http://localhost:8080/auth/contents/chapter/`,
        chapterId
      );
      if (status !== 200) throw new Error("status is not good");
      //렌더링 필요
    } catch (err) {
      console.error(err);
    }
  };

  const handleClassDeleteClick = async (uploadClassId: number) => {
    try {
      const status = await fetchDelete(
        `localhost:8080/auth/chapter/lecture/`,
        uploadClassId
      );
      if (status !== 200) throw new Error("status is not good");
      //렌더링 필요
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <>
      {curriculumInfo.map((e, index) => (
        <div key={index} className={styles.Wrapper}>
          <div className={styles.thumbnail}>
            <Image src={e.thumbnail} alt={e.title} fill={true} />
          </div>
          <div className={styles.chapterWrapper}>
            <div className={styles.chapterTitle}>
              <h4>{e.chapterOrder}</h4>
              <div className={styles.chapter}>
                <h3>{e.title}</h3>
                <span>
                  <Link
                    href={{
                      pathname: "/upload/chapter",
                      query: {
                        slug: "edit",
                        thumbnail: e.thumbnail,
                        title: e.title,
                        chapterOrder: e.chapterOrder,
                      },
                    }}
                  >
                    <button className={styles.btn}>수정</button>
                  </Link>
                  <button
                    onClick={() => handleChapterDeleteClick(e.chapterId)}
                    className={styles.btn}
                  >
                    삭제
                  </button>
                </span>
              </div>

              {e.uploadClassList.map((e, index) => (
                <>
                  <div className={styles.class}>
                    <div>
                      <h4 key={index}>{e.title}</h4>
                      <h5>영상길이</h5>
                    </div>
                    <div>
                      <Link
                        href={{
                          pathname: "/upload/class",
                          query: { slug: "edit" },
                        }}
                      >
                        <button className={styles.btn}>수정</button>
                      </Link>
                      <button
                        onClick={() => handleClassDeleteClick(e.uploadClassId)}
                        className={styles.btn}
                      >
                        삭제
                      </button>
                    </div>
                  </div>
                </>
              ))}

              <div className={styles.addbtnWrapper}>
                <Link href={"/upload/class"}>
                  <button className={styles.addbtn}>
                    <FontAwesomeIcon
                      icon={faPencil}
                      className={styles.fontimg}
                    />
                    강의 추가하기
                  </button>
                </Link>
              </div>
            </div>
          </div>
        </div>
      ))}
    </>
  );
};

export default CurriculumInfo;
