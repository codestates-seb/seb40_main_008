import React from "react";
import styles from "./CurriculumInfo.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPencil } from "@fortawesome/free-solid-svg-icons";
import { ICurriculumContent } from "../../types/contents";
import Image from "next/image";
import Link from "next/link";
import Curriculumdelete from "../Buttons/Curriculumdelete";
import { useRouter } from "next/navigation";

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
  const router = useRouter();
  return (
    <>
      {curriculumInfo.length === 0 ? (
        <div className={styles.nocurriculumWrapper}>
          업로드된 커리큘럼이 없습니다.
        </div>
      ) : (
        curriculumInfo.map((e, index) => (
          <div key={index} className={styles.Wrapper}>
            <div>
              <div className={styles.thumbnail}>
                <Image
                  src={e.thumbnail}
                  alt={e.title}
                  fill={true}
                  style={{ objectFit: "cover" }}
                />
              </div>
              <div className={styles.chapterWrapper}>
                <div className={styles.chapterTitle}>
                  <p style={{ fontSize: "15px", fontWeight: "bold" }}>
                    {e.chapterOrder}
                  </p>
                  <div className={styles.chapter}>
                    <p style={{ fontSize: "22px", fontWeight: "bold" }}>
                      {e.title}
                    </p>
                    <div>
                      {role == "creator" ? (
                        <>
                          <Link
                            href={{
                              pathname: "/upload/chapter",
                              query: {
                                slug: "edit",
                                chapterId: e.chapterId,
                                contentId: contentsId,
                                thumbnail: e.thumbnail,
                                title: e.title,
                                chapterOrder: e.chapterOrder,
                              },
                            }}
                          >
                            <button className={styles.btn}>수정</button>
                          </Link>

                          <Curriculumdelete
                            url={`https://pioneroroom.com/auth/contents/chapter/`}
                            Id={e.chapterId}
                          />
                        </>
                      ) : (
                        ""
                      )}
                    </div>
                  </div>

                  {e.uploadClassList.map((e, index) => (
                    <>
                      <div key={index} className={styles.class}>
                        <div>
                          <Link
                            href={{
                              pathname: `/contents/${contentsId}/video/${e.uploadClassId}`,
                            }}
                          >
                            <h4>{e.title}</h4>
                          </Link>
                        </div>
                        <div>
                          {role == "creator" ? (
                            <>
                              {/* <Link
                                href={{
                                  pathname: "/upload/class",
                                  query: { slug: "edit" },
                                }}
                              >
                                <button className={styles.btn}>수정</button>
                              </Link> */}

                              <Curriculumdelete
                                url={`https://pioneroroom.com/auth/chapter/lecture/`}
                                Id={e.uploadClassId}
                              />
                            </>
                          ) : (
                            ""
                          )}
                        </div>
                      </div>
                    </>
                  ))}

                  <div className={styles.addbtnWrapper}>
                    {role == "creator" ? (
                      <Link
                        href={{
                          pathname: "/upload/class",
                          query: {
                            chapterId: e.chapterId,
                            contentsId: contentsId,
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
                    ) : (
                      ""
                    )}
                  </div>
                </div>
              </div>
            </div>
          </div>
        ))
      )}
    </>
  );
};

export default CurriculumInfo;
