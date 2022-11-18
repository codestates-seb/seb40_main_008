import React from "react";
import styles from "./ProgressBar.module.css";

export const ProgressBar = () => {
  return (
    <div className={styles.progress}>
      <div className={styles.color}></div>
    </div>
  );
};
