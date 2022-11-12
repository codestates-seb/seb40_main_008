"use client";
import React, { useState, useEffect } from "react";
import Link from "next/link";
import styles from "./NavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const NavBar = () => {
  const [isLogin, setIsLogin] = useState(false);
  const [ScrollPosition, setScrollPosition] = useState(0);

  const updateScroll = () => {
    setScrollPosition(window.scrollY || document.documentElement.scrollTop);
  };

  useEffect(() => {
    window.addEventListener("scroll", updateScroll);

    return () => window.removeEventListener("scroll", updateScroll);
  });

  return (
    <nav className={ScrollPosition < 60 ? styles.nav : styles.change_nav}>
      <div>
        <FontAwesomeIcon icon={faBars} className={styles.font} />
        <a className={styles.logo}>clss4989</a>
      </div>
      <div>
        {isLogin ? (
          <Link href={"/"}>Logout</Link>
        ) : (
          <Link href={"/login"}>Login</Link>
        )}
      </div>
    </nav>
  );
};

export default NavBar;
