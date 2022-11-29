import { NextApiRequest, NextApiResponse } from 'next';

export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    // console.log('req', req);
    // const token = await getToken({ req, secret: process.env.JWT_SECRET as string });

    const accessToken = req.query.access_token;
    const refreshToken = req.query.refresh_token;

    if (accessToken) res.setHeader('Set-Cookie', accessToken);

    res.redirect(`/`);
    // res.status(200).json({ accessToken: accessToken, refreshToken: refreshToken });
}

// 토큰을 저장할 방법(local storage 접근)
// setCookie -> header에 authorization에 넣어서 보관하기

// 페이지 어느곳이든 access Token을 가지고 req (어떻게 관리하는지?)
// 토큰을 가지고 있을 function 설계 / 어디다 넣을지(페이지마다?)
// main으로 redirect
// getSession

// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiamh3b245MzA0QGdtYWlsLmNvbSIsInN1YiI6Impod29uOTMwNEBnbWFpbC5jb20iLCJpYXQiOjE2Njk2OTgzMzksImV4cCI6MTY2OTcwMDEzOX0.LA8Bodedi0pEk3Tew3iHMw2Be_ATbopSNnDXUyeqdDVRtTJ3JPCYzh7tqg04YCs2RDUJU3vFRrx4UtktRR71Rw
// eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJVU0VSIl0sInVzZXJuYW1lIjoiamh3b245MzA0QGdtYWlsLmNvbSIsInN1YiI6Impod29uOTMwNEBnbWFpbC5jb20iLCJpYXQiOjE2Njk2OTY1MzIsImV4cCI6MTY2OTY5ODMzMn0.p8oq456HMxpFctNFoK9_txidbPHgeiHJJeJFMe9tncK2ebZGf280UgDrwtgAUfleqT5oHQBjFBP57-yCl0T2Yw