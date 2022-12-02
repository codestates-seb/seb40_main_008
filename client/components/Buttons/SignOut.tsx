'use client'
import React from 'react'
import OrangeButton from './orangeButton'
import { signOut } from "next-auth/react";
import { deleteCookie, getCookie } from "cookies-next";

// accesstoken파기하고 redirect home

export const SignOut = () => {

    const token = getCookie("accessToken");

    const handleSignout = () => {

        // 로그아웃 함수 만드는 중

        // alert('signout');
        signOut();
    }

    return (
        <OrangeButton
            handleClick={handleSignout}
            name={"로그 아웃"}
        ></OrangeButton>
    )
}
