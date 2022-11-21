import { NextApiRequest, NextApiResponse } from 'next';
import { getToken } from 'next-auth/jwt';

// 계속해서 토큰값이 바뀜...

export default async function admintokentest(req: NextApiRequest, res: NextApiResponse) {

    const jwt = require('jsonwebtoken');

    const user = { id: 'admin' };
    const token = jwt.sign({ user }, 'my_secret_key');

    res.json({
        token: token
    })
}
