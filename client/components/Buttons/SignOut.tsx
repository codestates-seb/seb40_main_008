'use client'
import React from 'react'
import OrangeButton from './orangeButton'
import { deleteCookie, getCookie } from "cookies-next";
import { useRouter } from 'next/navigation';

// accesstoken파기하고 redirect home

export const SignOut = () => {

    const router = useRouter();

    // const token = getCookie("accessToken");

    const handleSignout = async () => {
        try {
            const res = await fetch('/api/logout');
            console.log("🚀 ~ file: SignOut.tsx:18 ~ handleSignout ~ res", res)
            if (res.ok) {
                router.push('/');
            }
        } catch (error) {
            console.log('err', error);
        }
    }

    return (
        <OrangeButton
            handleClick={handleSignout}
            name={"로그 아웃"}
        ></OrangeButton>
    )
}
