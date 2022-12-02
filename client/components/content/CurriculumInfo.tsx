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
  role: "creator" | "Unpaid_customer" | "Paid_customer";
}

const CurriculumInfo = ({
  role,
  contentsId,
  curriculumInfo,
}: CurriculumInfoProps) => {
  console.log("dadsa", contentsId);

  const handleChapterDeleteClick = async (chapterId: number) => {
    try {
      const status = await fetchDelete(
        `https://pioneroroom.com/auth/contents/chapter/`,
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
        `https://pioneroroom.com/auth/chapter/lecture/`,
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
      {curriculumInfo &&
        curriculumInfo.map((e, index) => (
          <div key={index} className={styles.Wrapper}>
            <div className={styles.thumbnail}>
              <Image src={e.thumbnail} alt={e.title} fill={true} />
            </div>
            <div className={styles.chapterWrapper}>
              <div className={styles.chapterTitle}>
                <h4>{e.chapterOrder}</h4>
                <div className={styles.chapter}>
                  <h3>{e.title}</h3>
                  {/* <h1>{role}</h1> */}
                  <span>
                    {role == "creator" ? (
                      <>
                        <Link
                          href={{
                            pathname: "/upload/chapter",
                            query: {
                              slug: "edit",
                              chapterId: e.chapterId,
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
                      </>
                    ) : (
                      ""
                    )}
                  </span>
                </div>

                {e.uploadClassList.map((e, index) => (
                  <>
                    <div key={index} className={styles.class}>
                      <div>
                        <Link
                          href={{
                            pathname: "/video",
                            query: { classID: e.uploadClassId },
                          }}
                        >
                          <h4>{e.title}</h4>
                          <h1>{role}</h1>
                        </Link>
                      </div>
                      <div>
                        {role == "creator" ? (
                          <>
                            <Link
                              href={{
                                pathname: "/upload/class",
                                query: { slug: "edit" },
                              }}
                            >
                              <button className={styles.btn}>수정</button>
                            </Link>
                            <button
                              onClick={() =>
                                handleClassDeleteClick(e.uploadClassId)
                              }
                              className={styles.btn}
                            >
                              삭제
                            </button>
                          </>
                        ) : (
                          ""
                        )}
                      </div>
                    </div>
                  </>
                ))}

                <div className={styles.addbtnWrapper}>
                  <Link
                    href={{
                      pathname: "/upload/class",
                      query: {
                        chapterId: e.chapterId,
                      },
                    }}
                  >
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
