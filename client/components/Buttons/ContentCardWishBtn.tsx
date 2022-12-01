"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCartShopping } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { patchWish } from "../../api/fetchWish";
interface ContentCardWishProps {
  contentId: number;
}

export const ContentCardWishBtn = (props: ContentCardWishProps) => {
  const { contentId } = props;

  const [wish, setWish] = useState(false);

  const handleWishCheck = () => {
    patchWish(contentId, wish).then((res) => {
      console.log(
        "🚀 ~ file: ContentCardWishBtn.tsx:19 ~ patchWish ~ res",
        res
      );
      if (res === 200) {
        setWish((prev) => !prev);
      }
    });
  };

  return (
    <>
      <button onClick={handleWishCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          color="red"
          icon={faCartShopping}
          className={`${wish ? styles.icon : styles.clickicon}`}
        />
        북마크
      </button>
    </>
  );
};
