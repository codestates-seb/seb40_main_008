"use client";
import React from "react";
import styles from "./BaseNavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleLeft } from "@fortawesome/free-solid-svg-icons";

const BaseNavbar = () => {
  return (
    <nav className={styles.baseNav}>
      <button onClick={() => window.history.back()} className={styles.leftbtn}>
        <FontAwesomeIcon icon={faAngleLeft} className={styles.font} />
      </button>
    </nav>
  );
};

export default BaseNavbar;
