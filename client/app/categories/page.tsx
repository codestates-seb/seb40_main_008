import React from "react";
import BaseNavbar from "../../components/BaseNavBar/BaseNavbar";
import styles from "./categories.module.css";
import Link from "next/link";

const Categories = () => {
  return (
    <>
      <BaseNavbar />
      <div className={styles.categorytop}>
        <span className={styles.categoryleft}>
          <span className={styles.link}>
            <Link href={"/categories/digitaldrawing"}>PROGRAMMING</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/drawing"}>드로잉</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/crafts"}>공예</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/food_beverage"}>요리/음료</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/baking_dessert"}>베이킹/디저트</Link>
          </span>
        </span>
        <span className={styles.categoryright}>
          <span className={styles.link}>
            <Link href={"/categories/music"}>음악</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/exercise"}>운동</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/lifestyle"}>라이프스타일</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/photo_video"}>사진/영상</Link>
          </span>
        </span>
      </div>

      <div className={styles.line}></div>

      <div className={styles.category}>
        <span className={styles.categoryleft}>
          <span className={styles.link}>
            <Link href={"/categories/finance"}>금융/재테크</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/startup"}>창업/부업</Link>
          </span>
        </span>
        <span className={styles.categoryright}>
          <span className={styles.link}>
            <Link href={"/categories/mind"}>성공마인드</Link>
          </span>
        </span>
      </div>

      <div className={styles.line}></div>

      <div className={styles.category}>
        <span className={styles.categoryleft}>
          <span className={styles.link}>
            <Link href={"/categories/programming"}>프로그래밍</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/datascience"}>데이터사이언스</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/product_planning"}>제품 기획</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/business"}>비즈니스</Link>
          </span>
        </span>
        <span className={styles.categoryright}>
          <span className={styles.link}>
            <Link href={"/categories/productivity"}>생산성</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/marketing"}>마케팅</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/design"}>디자인</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/video_3d"}>영상/3D</Link>
          </span>
        </span>
      </div>

      <div className={styles.line}></div>

      <div className={styles.category}>
        <span className={styles.categoryleft}>
          <span className={styles.link}>
            <Link href={"/categories/english"}>영어</Link>
          </span>
          <span className={styles.link}>
            <Link href={"/categories/second_language"}>제2 외국어</Link>
          </span>
        </span>
        <span className={styles.categoryright}>
          <span className={styles.link}>
            <Link href={"/categories/language_test"}>외국어 시험</Link>
          </span>
        </span>
      </div>

      <div className={styles.line}></div>

      <div className={styles.category}>
        <span className={styles.categoryleft}>
          <span className={styles.link}>
            <Link href={"/categories/child_edu"}>아이 교육</Link>
          </span>
        </span>
        <span className={styles.categoryright}>
          <span className={styles.link}>
            <Link href={"/categories/parents_edu"}>부모 교육</Link>
          </span>
        </span>
      </div>
    </>
  );
};

export default Categories;
