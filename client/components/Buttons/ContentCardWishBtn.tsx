"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { getCookie } from "cookies-next";
import { patchWish } from "../../api/fetchWish";
interface ContentCardWishProps {
  contentId: number;
}

export const ContentCardWishBtn = (props: ContentCardWishProps) => {
  const { contentId } = props;

  const [wish, setWish] = useState(false);

  const handleWishCheck = () => {
    setWish(!wish);
    console.log("wish", wish);
  };

  useEffect(() => {
    // fetch(`https://pioneroroom.com/auth/${contentId}/wish`, {
    //   method: "POST",
    //   headers: {
    //     Authorization: `Bearer ${token}`,
    //   },
    //   body: JSON.stringify({ wish: wish }),
    // })
    //   .then((res) => {
    //     res.json();
    //   })
    //   .then((data) => {
    //     console.log("성공", data);
    //   })
    //   .catch((error) => {
    //     setWish(!wish);
    //   });
    patchWish(contentId, wish).then((res) => {
      if (res !== 200) {
        setWish(!wish);
      }
    });
  }, [wish]);

  return (
    <>
      <button onClick={handleWishCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          icon={faBookmark}
          className={`${wish ? styles.icon : styles.clickicon}`}
        />
        북마크
      </button>
    </>
  );
};
