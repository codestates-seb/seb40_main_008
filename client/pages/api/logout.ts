import { NextApiRequest, NextApiResponse } from 'next';
import { redirect } from 'next/dist/server/api-utils';

export default function handler(req: NextApiRequest, res: NextApiResponse) {
    console.log("🚀 ~ file: logOut.ts:6 ~ handler ~ req", req);
    res.setHeader(
        "Set-Cookie", [
        `accessToken=deleted; Max-Age=0`,
    ]
    );
    redirect(res, 301, `/`);
}

