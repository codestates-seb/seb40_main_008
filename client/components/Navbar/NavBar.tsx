"use client";

import React, { useState } from "react";
import Link from "next/link";
import styles from "./NavBar.module.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const NavBar = () => {
  const [isLogin, setIsLogin] = useState(false);
  return (
    <nav className={styles.nav}>
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
