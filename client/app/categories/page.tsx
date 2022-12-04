import React from "react";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import styles from "./categories.module.css";
import Link from "next/link";

// 카테고리링크 누르면 categoryName 보내기

const Categories = () => {
  return (
    <>
      <BaseNavbar />
      <div className={styles.categoryWrapper}>
        <div className={styles.categorytop}>
          <span className={styles.categoryleft}>
            <span className={styles.link}>
              <Link
                style={{ color: "gray" }}
                href={"/categories/DIGITAL_DRAWING"}
              >
                디지털 드로잉
              </Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/DRAWING"}>드로잉</Link>
            </span>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/CRAFTS"}>
                공예
              </Link>
            </span>
            <span className={styles.link}>
              <Link
                style={{ color: "gray" }}
                href={"/categories/COOKING_BEVERAGE"}
              >
                요리/음료
              </Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/BAKING_DESSERT"}>베이킹/디저트</Link>
            </span>
          </span>
          <span className={styles.categoryright}>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/MUSIC"}>
                음악
              </Link>
            </span>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/exercise"}>
                운동
              </Link>
            </span>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/LIFE_STYLE"}>
                라이프스타일
              </Link>
            </span>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/PHOTO_MEDIA"}>
                사진/영상
              </Link>
            </span>
          </span>
        </div>

        <div className={styles.line}></div>

        <div className={styles.category}>
          <span className={styles.categoryleft}>
            <span className={styles.link}>
              <Link style={{ color: "gray" }} href={"/categories/FINANCE"}>
                금융/재테크
              </Link>
            </span>
            <span className={styles.link}>
              <Link
                style={{ color: "gray" }}
                href={"/categories/FOUNDED_SIDELINE"}
              >
                창업/부업
              </Link>
            </span>
          </span>
          <span className={styles.categoryright}>
            <span className={styles.link}>
              <Link href={"/categories/SUCCESS_MIND"}>성공마인드</Link>
            </span>
          </span>
        </div>

        <div className={styles.line}></div>

        <div style={{ color: "gray" }} className={styles.category}>
          <span className={styles.categoryleft}>
            <span className={styles.link}>
              <Link href={"/categories/PROGRAMMING"}>프로그래밍</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/DATA_SCIENCE"}>데이터사이언스</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/PRODUCT_PLANNING"}>제품 기획</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/BUSINESS"}>비즈니스</Link>
            </span>
          </span>
          <span className={styles.categoryright}>
            <span className={styles.link}>
              <Link href={"/categories/PRODUCTIVITY"}>생산성</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/MARKETING"}>마케팅</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/design"}>디자인</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/VIDEO_3D"}>영상/3D</Link>
            </span>
          </span>
        </div>

        <div className={styles.line}></div>

        <div style={{ color: "gray" }} className={styles.category}>
          <span className={styles.categoryleft}>
            <span className={styles.link}>
              <Link href={"/categories/ENGLISH"}>영어</Link>
            </span>
            <span className={styles.link}>
              <Link href={"/categories/SECOND_LANGUAGE"}>제2 외국어</Link>
            </span>
          </span>
          <span className={styles.categoryright}>
            <span className={styles.link}>
              <Link href={"/categories/LANGUAGE_EXAM"}>외국어 시험</Link>
            </span>
          </span>
        </div>

        <div className={styles.line}></div>

        <div style={{ color: "gray" }} className={styles.category}>
          <span className={styles.categoryleft}>
            <span className={styles.link}>
              <Link href={"/categories/CHILD_EDUCATION"}>아이 교육</Link>
            </span>
          </span>
          <span className={styles.categoryright}>
            <span className={styles.link}>
              <Link href={"/categories/PARENTS_EDUCATION"}>부모 교육</Link>
            </span>
          </span>
        </div>
      </div>
    </>
  );
};

export default Categories;
