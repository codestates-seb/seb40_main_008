"use client";
import React from "react";
import styles from "./BaseNavbar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";
import { UseScrollBar } from "../../hooks/\bScrollBar/UseScrollBar";

const BaseNavbar = () => {
  const { show } = UseScrollBar();
  return (
    <>
      {window.scrollY < 45 ? (
        <nav className={styles.firstNav}>
          <button
            onClick={() => window.history.back()}
            className={styles.leftbtn}
          >
            <FontAwesomeIcon icon={faAngleLeft} className={styles.font} />
          </button>
        </nav>
      ) : (
        <nav className={`${show ? styles.baseNav : styles.change_nav} `}>
          <button
            onClick={() => window.history.back()}
            className={styles.leftbtn}
          >
            <FontAwesomeIcon icon={faAngleLeft} className={styles.font} />
          </button>
        </nav>
      )}
    </>
  );
};

export default BaseNavbar;
