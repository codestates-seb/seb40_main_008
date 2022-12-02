"use client";
import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import styles from "../content/ContentInfo.module.css";
import { getCookie } from "cookies-next";
interface ContentCardFavoriteProps {
  contentId: number;
}

export const ContentCardFavoriteBtn = ({
  contentId,
}: ContentCardFavoriteProps) => {
  const [like, setLike] = useState(false);
  const token = getCookie("accessToken");

  const handleLikeCheck = () => {
    setLike(!like);
  };

  useEffect(() => {
    fetch(`https://pioneroroom.com/auth/${contentId}/likes`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        res.json();
      })
      .then((data) => {
        console.log("성공", data);
      })
      .catch((error) => {
        setLike(!like);
      });
  }, [like]);

  return (
    <>
      <button onClick={handleLikeCheck} className={styles.zzimbtn}>
        <FontAwesomeIcon
          icon={faHeart}
          className={`${like ? styles.icon : styles.clickicon}`}
        />
        좋아요
      </button>
    </>
  );
};
