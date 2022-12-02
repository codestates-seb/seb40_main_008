'use client'
import React from 'react'
import OrangeButton from './orangeButton'
import { deleteCookie, getCookie } from "cookies-next";

// accesstoken파기하고 redirect home

export const SignOut = () => {

    const token = getCookie("accessToken");

    const handleSignout = () => {
        fetch(`/api/logout`);
    }

    return (
        <OrangeButton
            handleClick={handleSignout}
            name={"로그 아웃"}
        ></OrangeButton>
    )
}
