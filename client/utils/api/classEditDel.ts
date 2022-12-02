import { getCookie } from "cookies-next";
// import { cookies } from 'next/headers';

export const editMyClass = async (id: number) => {

    // const token = cookies().get('accessToken')?.value;
    const token = getCookie('accessToken');
    console.log("🚀 ~ file: classEditDel.ts:6 ~ editMyClass ~ token", token)

    try {
        const response = await fetch(`http://pioneroroom/auth/contents/${String(id)}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        const res = await response.json();
        return res;
    } catch (error) {
        console.error(error);
    }

}

export const delMyClass = async (id: number) => {

    const token = getCookie("accessToken");

    try {
        const response = await fetch(`http://pioneroroom/auth/contents/${String(id)}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
            }
        );
        const res = await response.json();
        return res;
    } catch (error) {
        console.error(error);
    }

}

