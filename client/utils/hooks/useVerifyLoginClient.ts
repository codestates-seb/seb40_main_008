import { getCookie } from 'cookies-next';
import React, { useState, useEffect } from 'react';

export const useVerifyLoginClient = () => {
    const [userInfo, setUserInfo] = useState(null);

    useEffect(() => {
        async function getUserInfo() {
            try {
                const token = getCookie('accessToken');
                if (!token) return;

                const response = await fetch(
                    'https://pioneroroom.com/auth/userinfo',
                    {
                        method: 'GET',
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    }
                );

                const userInfo = await response.json();
                return userInfo;

            } catch (error) {
                console.error(error);
                return null;
            }
        }
        getUserInfo().then((userInfoOrNull) => setUserInfo(userInfoOrNull));
    }, []);
    return userInfo;
};