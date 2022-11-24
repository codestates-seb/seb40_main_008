import React from "react";
import { signIn, signOut, useSession } from "next-auth/react";
import { unstable_getServerSession } from "next-auth";
import { getSession } from "../../utils/helper/session";
import SignInButton from "../../components/Buttons/SignInButton";
import { headers } from "next/headers";
import Link from "next/link";
import styles from "./login.module.css";

// 토큰 string으로 넘겨드리기 -> json으로 받아서

const LoginPage = async () => {
  const session = await getSession(headers().get("cookie") ?? "");

  return (
    <div className={styles.loginWrapper}>
      <Link href={"/"}>Home</Link>
      <p>User: {session?.user?.email || "nothing"}</p>
      <SignInButton isSignIn={!!session} />
    </div>
  );
};

export default LoginPage;
